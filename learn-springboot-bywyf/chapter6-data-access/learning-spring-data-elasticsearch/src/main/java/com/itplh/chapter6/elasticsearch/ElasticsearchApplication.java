package com.itplh.chapter6.elasticsearch;

import com.itplh.chapter6.elasticsearch.domain.Address;
import com.itplh.chapter6.elasticsearch.domain.Child;
import com.itplh.chapter6.elasticsearch.domain.Gender;
import com.itplh.chapter6.elasticsearch.domain.Person;
import com.itplh.chapter6.elasticsearch.repository.PersonRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.range.ParsedRange;
import org.elasticsearch.search.aggregations.metrics.avg.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.max.ParsedMax;
import org.elasticsearch.search.aggregations.metrics.min.ParsedMin;
import org.elasticsearch.search.aggregations.metrics.stats.ParsedStats;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.valuecount.ParsedValueCount;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author: tanpenggood
 * @date: 2020-10-21 22:41
 */
@SpringBootApplication
public class ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }

    /**
     * 插入数据
     *
     * @author: tanpenggood
     * @date: 2020-10-21 23:57
     */
    @Bean
    CommandLineRunner saveAll(PersonRepository personRepository) {
        return args -> {
            personRepository.deleteAll();

            Address address = new Address("chong qing", "chong qing");
            Collection<Child> children = Arrays.asList(new Child("tyn", Gender.FEMALE), new Child("tbe", Gender.MALE));
            Person person = new Person("tp", 25, address, children);

            Address address1 = new Address("bei jing", "bei jing");
            Collection<Child> children1 = Arrays.asList(new Child("aaa", Gender.FEMALE), new Child("bbb", Gender.MALE));
            Person person1 = new Person("foo", 24, address1, children1);

            Address address2 = new Address("shang hai", "shang hai");
            Collection<Child> children2 = Arrays.asList(new Child("ccc", Gender.FEMALE), new Child("ddd", Gender.MALE));
            Person person2 = new Person("bar", 26, address2, children2);

            personRepository.saveAll(Arrays.asList(person, person1, person2));
        };
    }

    /**
     * 查 jpa方式
     *
     * @author: tanpenggood
     * @date: 2020-10-21 23:57
     */
    @Bean
    CommandLineRunner query(PersonRepository personRepository) {
        return args -> {
            List<Person> person1 = personRepository.findByName("tp");
            List<Person> person2 = personRepository.findByAddress_City("bei jing");
            List<Person> person3 = personRepository.findByChildren_Name("ccc");
            Page<Person> personPage = personRepository.findByAgeRange(20, 30,
                    PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "age")));
            person1.forEach(System.out::println);
            person2.forEach(System.out::println);
            person3.forEach(System.out::println);
            System.out.printf("总数为：%s 总页数为：%s\n",
                    personPage.getTotalElements(),
                    personPage.getTotalPages());
            personPage.forEach(System.out::println);
        };
    }

    /**
     * 查 search方式
     *
     * @author: tanpenggood
     * @date: 2020-10-21 23:57
     */
    @Bean
    CommandLineRunner queryBuilderAndSearchQuery(PersonRepository personRepository) {
        return args -> {
            QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(queryBuilder)
                    .withPageable(PageRequest.of(0, 3, Sort.by("age")))
                    .build();

            Page<Person> personPage1 = personRepository.search(queryBuilder, PageRequest.of(0, 3, Sort.by("age")));
            Page<Person> personPage2 = personRepository.search(searchQuery);
            System.out.printf("personPage1 总数为：%s 总页数为：%s\n",
                    personPage1.getTotalElements(),
                    personPage1.getTotalPages());
            personPage1.forEach(System.out::println);
            System.out.printf("personPage2 总数为：%s 总页数为：%s\n",
                    personPage2.getTotalElements(),
                    personPage2.getTotalPages());
            personPage2.forEach(System.out::println);
        };
    }

    /**
     * 查 search方式
     *
     * @author: tanpenggood
     * @date: 2020-10-22 00:12
     */
    @Bean
    CommandLineRunner search(PersonRepository personRepository) {
        return args -> {
            SearchQuery queryByAgeRangeAndNameRegex = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.rangeQuery("age").from(20).to(25))
                    .withFilter(QueryBuilders.matchQuery("name", "tp"))
                    .build();
            SearchQuery fuzzyQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.fuzzyQuery("address.city", "shang").fuzziness(Fuzziness.AUTO))
                    .build();
            SearchQuery anotherFuzzyQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.matchQuery("address.city", "jing").fuzziness(Fuzziness.AUTO))
                    .build();

            Page<Person> personPage1 = personRepository.search(queryByAgeRangeAndNameRegex);
            Page<Person> personPage2 = personRepository.search(fuzzyQuery);
            Page<Person> personPage3 = personRepository.search(anotherFuzzyQuery);

            personPage1.forEach(System.out::println);
            System.out.println("-------------------");
            personPage2.forEach(System.out::println);
            System.out.println("-------------------");
            personPage3.forEach(System.out::println);
            System.out.println("-------------------");
        };
    }

    /**
     * 查询统计
     *
     * @author: tanpenggood
     * @date: 2020-10-22 00:24
     */
    @Bean
    CommandLineRunner aggregateQuery(ElasticsearchOperations elasticsearchOperations) {
        return args -> {
            SearchQuery aggregateQuery = new NativeSearchQueryBuilder()
                    .withIndices("person")
                    .addAggregation(AggregationBuilders.sum("sumAge").field("age"))
                    .addAggregation(AggregationBuilders.max("maxAge").field("age"))
                    .addAggregation(AggregationBuilders.min("minAge").field("age"))
                    .addAggregation(AggregationBuilders.count("countAge").field("age"))
                    .addAggregation(AggregationBuilders.avg("avgAge").field("age"))
                    .addAggregation(AggregationBuilders.stats("ageInfo").field("age"))
                    .addAggregation(AggregationBuilders.range("ageRange").field("age").addRange(20, 25))
                    .addAggregation(AggregationBuilders.filter("leftPerson",
                            QueryBuilders.rangeQuery("age").from(20).to(25))
                            .subAggregation(AggregationBuilders.sum("leftSum").field("age")))
                    .build();

            Aggregations aggs = elasticsearchOperations.query(aggregateQuery, SearchResponse::getAggregations);

            double sum = ((ParsedSum) aggs.get("sumAge")).getValue();
            System.out.println("sum age is " + sum);
            double max = ((ParsedMax) aggs.get("maxAge")).getValue();
            System.out.println("max age is " + max);
            double min = ((ParsedMin) aggs.get("minAge")).getValue();
            System.out.println("min age is " + min);
            double avg = ((ParsedAvg) aggs.get("avgAge")).getValue();
            System.out.println("avg age is " + avg);
            double count = ((ParsedValueCount) aggs.get("countAge")).getValue();
            System.out.println("count age is " + count);

            ParsedStats stats = aggs.get("ageInfo");
            System.out.println("sum age is " + stats.getSumAsString());
            System.out.println("max age is " + stats.getMaxAsString());
            System.out.println("min age is " + stats.getMinAsString());
            System.out.println("avg age is " + stats.getAvgAsString());
            System.out.println("count age is " + stats.getCount());


            ((ParsedRange) aggs.get("ageRange")).getBuckets().forEach(bucket -> {
                System.out.printf("%s到%s 数量为：%s\n",
                        bucket.getFrom(),
                        bucket.getTo(),
                        bucket.getDocCount());
            });

            Aggregations filterAggs = ((ParsedFilter) aggs.get("leftPerson")).getAggregations();
            double leftSum = ((ParsedSum) filterAggs.get("leftSum")).getValue();
            System.out.println("left sum is " + leftSum);
        };
    }

}
