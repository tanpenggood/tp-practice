package com.itplh.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-19 14:09
 * @version: v1.0.0
 */
@Data
@ApiModel("用户查询对象模型")
public class UserModel {

    private String username;
    @ApiModelProperty("用户年龄起始值")
    private String age;
    @ApiModelProperty("用户年龄终止值")
    private String ageTo;
    private String xxx;

}
