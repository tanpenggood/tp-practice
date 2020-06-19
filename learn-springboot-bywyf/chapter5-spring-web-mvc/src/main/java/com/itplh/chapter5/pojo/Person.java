package com.itplh.chapter5.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: tanpenggood
 * @since: 2020-06-19 22:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @NotNull(message = "id不能为空")
    private Long id;
    @Size(min = 3, max = 5, message = "name在 3 到 5 个字符之间")
    private String name;
    @Min(value = 18, message = "年龄不能低于 18 岁")
    private Integer age;

}
