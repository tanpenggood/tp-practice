# itplh-security

>spring boot 1.5.20.RELEASE + spring cloud Dalston.SR2
>
>spring boot 2.1.13.RELEASE + spring cloud Greenwich.SR5

## 项目简介

- spring-boot-1x
    - 学习 Spring Security
    - 学习编写 REST 接口测试用例
    - 学习自定义 validator
    - 学习自定义 filter
    - 学习自定义 interceptor
    - 学习自定义 aspect
    - 学习 REST 接口多线程编程
        - itplh-security-demo 
            - `com.itplh.web.async.AsyncCallableController`
            - `com.itplh.web.async.AsyncDeferredResultController`
            - `com.itplh.web.async.AsyncResponseBodyEmitterController`
- spring-boot-2x
    - itplh-security-distributed 分布式用户认证中心(基于OAuth2.0)

## 快速开始

- spring-boot-1x
    - 启动并配置mysql，创建空数据库`itplh-security-demo`
    - 启动并配置redis
    - 启动main类 itplh-security-demo `com.itplh.DemoApplication`
    - 访问`http://localhost:8080/sign-in.html`
        - 用户名：任意
        - 密码：123456

- spring-boot-2x
