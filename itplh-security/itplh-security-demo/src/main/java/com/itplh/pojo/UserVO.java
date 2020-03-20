package com.itplh.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-19 15:32
 * @version: v1.0.0
 */
@Data
@ApiModel("用户对象模型")
public class UserVO {

    public interface UserSimpleView {}
    public interface UserDetailView extends UserSimpleView {}

    @JsonView(UserSimpleView.class)
    private String id;

    @JsonView(UserSimpleView.class)
    @ApiModelProperty("用户名")
    private String username;

    @JsonView(UserDetailView.class)
    @ApiModelProperty("用户密码")
    private String password;

    @JsonView(UserSimpleView.class)
    @ApiModelProperty("用户生日")
    private Date birthday;

}
