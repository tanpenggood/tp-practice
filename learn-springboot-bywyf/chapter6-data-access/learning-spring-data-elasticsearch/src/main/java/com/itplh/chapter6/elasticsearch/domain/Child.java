package com.itplh.chapter6.elasticsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: tanpenggood
 * @date: 2020-10-21 22:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Child {
    private String name;
    private Gender gender;
}
