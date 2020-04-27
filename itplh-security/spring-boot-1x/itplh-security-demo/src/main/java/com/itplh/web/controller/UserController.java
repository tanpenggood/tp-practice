package com.itplh.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.itplh.pojo.UserCreateModel;
import com.itplh.pojo.UserModel;
import com.itplh.pojo.UserUpdateModel;
import com.itplh.pojo.UserVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-19 13:17
 * @version: v1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * @description: 获取当前用户
     * 方式一 {@link SecurityContextHolder#getContext()#getAuthentication()}
     * 方式二 Authentication user
     * 方式三 @AuthenticationPrincipal UserDetails user
     * @author: tanpeng
     * @date : 2020/4/22 13:16
     * @version: v1.0.0
     */
    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

    @GetMapping
    @JsonView(UserVO.UserSimpleView.class)
    @ApiOperation("用户查询服务")
    public List<UserVO> query(UserModel userModel) {

        System.out.println(userModel.toString());

        List<UserVO> users = new ArrayList<>();
        users.add(new UserVO());
        users.add(new UserVO());
        users.add(new UserVO());
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(UserVO.UserDetailView.class)
    public UserVO getInfo(@ApiParam("用户id") @PathVariable String id) {
        System.out.println("welcome in getInfo service");
        UserVO userVO = new UserVO();
        userVO.setUsername("tanpeng");
        return userVO;
    }

    @PostMapping()
    public UserVO create(@Valid @RequestBody UserCreateModel user) {
        System.out.println(user.toString());
        user.setId("1");
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @PutMapping("/{id:\\d+}")
    public UserVO update(@Valid @RequestBody UserUpdateModel user, BindingResult errors) {
        System.out.println(user.toString());
        user.setId("1");
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }
}
