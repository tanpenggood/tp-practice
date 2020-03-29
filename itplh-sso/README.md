# itplh-sso

单点登录 oauth2.0 + jwt

> spring boot 1.5.20.RELEASE
>
> spring cloud Dalston.SR2

## 项目简介

- itplh-sso-server 认证服务器
- itplh-sso-client1 客户端1
- itplh-sso-client2 客户端2

## 快速开始

1. 更改`hosts`配置
    ```
    127.0.0.1 sso.itplh.com
    127.0.0.1 client1.itplh.com
    127.0.0.1 client2.itplh.com
    ```

2. 启动`itplh-sso-server`
3. 启动`itplh-sso-client1` `itplh-sso-client2`
4. 访问
    ```aidl
    http://client1.itplh.com:8081
    http://client2.itplh.com:8082
    ```