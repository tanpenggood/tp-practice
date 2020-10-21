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
public class Address {
    private String city;
    private String province;
}
