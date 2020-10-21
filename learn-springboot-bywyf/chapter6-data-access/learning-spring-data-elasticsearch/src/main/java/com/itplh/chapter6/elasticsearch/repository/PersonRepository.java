package com.itplh.chapter6.elasticsearch.repository;

import com.itplh.chapter6.elasticsearch.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author: tanpenggood
 * @date: 2020-10-21 22:54
 */
public interface PersonRepository extends ElasticsearchRepository<Person, String> {
    List<Person> findByName(String name);

    List<Person> findByAddress_City(String city);

    List<Person> findByChildren_Name(String childrenName);

    @Query("{\"bool\": {\"must\": {\"range\": {\"age\": {\"gte\": \"?0\", \"lte\": \"?1\"}}}}}")
    Page<Person> findByAgeRange(Integer startAge, Integer endAge, Pageable pageable);
}
