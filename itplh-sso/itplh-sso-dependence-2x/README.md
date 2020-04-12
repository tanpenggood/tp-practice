# itplh-sso-dependence-2x

单点登录 oauth2.0 + jwt

> spring boot 2.1.13.RELEASE
>
> spring cloud Greenwich.SR5

## 快速开始

1. 更改`hosts`配置
    ```
    127.0.0.1 sso.itplh.com
    127.0.0.1 client1.itplh.com
    127.0.0.1 client2.itplh.com
    ```

2. 启动`itplh-sso-2x-server`
3. 启动`itplh-sso-2x-client1` `itplh-sso-2x-client2`
4. 访问
    ```aidl
    http://client1.itplh.com:9091
    http://client2.itplh.com:9092
    ```

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
        <groupId>org.springframework.security.oauth.boot</groupId>
        <artifactId>spring-security-oauth2-autoconfigure</artifactId>
        <version>2.1.13.RELEASE</version>
    </dependency>
</dependencies>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.1.13.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Greenwich.SR5</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```