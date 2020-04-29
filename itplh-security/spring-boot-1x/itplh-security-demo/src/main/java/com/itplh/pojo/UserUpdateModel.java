package com.itplh.pojo;

import com.itplh.validator.CustomConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-19 15:54
 * @version: v1.0.0
 */
@Data
public class UserUpdateModel {

    private String id;
    @CustomConstraint(message = "自定义验证注解")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Past(message = "生日必须是过去的时间")
    private Date birthday;

}

