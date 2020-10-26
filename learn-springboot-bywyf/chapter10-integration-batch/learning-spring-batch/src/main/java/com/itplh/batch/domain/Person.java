package com.itplh.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: tanpenggood
 * @date: 2020-10-26 22:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
}
