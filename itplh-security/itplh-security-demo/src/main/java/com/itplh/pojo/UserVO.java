package com.itplh.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-19 15:32
 * @version: v1.0.0
 */
@Data
public class UserVO {

    public interface UserSimpleView {}
    public interface UserDetailView extends UserSimpleView {}

    @JsonView(UserSimpleView.class)
    private String id;

    @JsonView(UserSimpleView.class)
    private String username;

    @JsonView(UserDetailView.class)
    private String password;

    @JsonView(UserSimpleView.class)
    private Date birthday;

}
