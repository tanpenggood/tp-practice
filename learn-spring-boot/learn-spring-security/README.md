# learn-spring-security

> Spring Security 实现细粒度权限认证(ABAC)

## 目录结构

- learn-spring-security
    - `db` sql文件
    - com.itplh.security
        - `config`
            - WebSecurityConfig Web安全配置(核心)
            - MethodSecurityConfig 开启方法授权配置
        - controller 
            - MethodSecurityPrePostController 方法授权资源1
            - MethodSecuritySecuredController 方法授权资源2
            - ResourceController Web授权资源
        - service
            - MyUserDetailsService 自定义loadUserByUsername

## 快速开始

1. 执行sql文件，`db/user_db.sql`
2. 启动`com.itplh.security.SpringSecurityApplication`
3. 访问 `http://localhost:8080/login-success`
4. 登录账号(密码) `su(111)` 、`huan(222)`
5. 访问资源进行测试

## Spring Security支持的SpEL表达式

Spring Security 支持的所有SpEL表达式如下：
 - `authentication`　　	用户认证对象
 - `hasRole(role)`	如果用户被授予了指定的角色，结果为true
 - `hasAnyRole(list of roles)` 如果用户拥有指定的任意角色，结果为true
 - `hasAuthority(authority)` 如果用户被授予了指定的权限，结果为true
 - `hasAnyAuthority(list of authorities)` 如果用户被授予了指定的权限，结果为true
 - `hasIpAddress(IP Address)`	用户地址
 - `isAnonymous()` 匿名用户，结果为true
 - `isAuthenticated()` 已认证用户，结果为true
 - `isFullyAuthenticated`　　	不是匿名也不是remember-me认证
 - `isRememberMe()`　　	remember-me认证
 - `permitAll`	始终true
 - `denyAll`　　	结果始终为false
 - `principal`	用户主要信息对象

## 项目依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.18</version>
    </dependency>
</dependencies>
```
