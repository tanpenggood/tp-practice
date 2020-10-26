package com.itplh.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: tanpenggood
 * @date: 2020-10-26 21:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvPerson {
    private String name;
    private String gender;
    private Integer age;
}
