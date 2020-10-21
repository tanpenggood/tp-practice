package com.itplh.chapter6.elasticsearch;

import com.itplh.chapter6.elasticsearch.domain.Address;
import com.itplh.chapter6.elasticsearch.domain.Child;
import com.itplh.chapter6.elasticsearch.domain.Gender;
import com.itplh.chapter6.elasticsearch.domain.Person;
import com.itplh.chapter6.elasticsearch.repository.PersonRepository;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

}
