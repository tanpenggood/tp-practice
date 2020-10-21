package com.itplh.chapter6.elasticsearch;

import com.itplh.chapter6.elasticsearch.domain.Address;
import com.itplh.chapter6.elasticsearch.domain.Child;
import com.itplh.chapter6.elasticsearch.domain.Gender;
import com.itplh.chapter6.elasticsearch.domain.Person;
import com.itplh.chapter6.elasticsearch.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author: tanpenggood
 * @date: 2020-10-21 22:41
 */
@SpringBootApplication
public class ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }

    @Bean
    CommandLineRunner saveAll(PersonRepository personRepository) {
        return args -> {
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

}
