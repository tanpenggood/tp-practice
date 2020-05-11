# bk-java

> 抓取贝壳真房源
>
> Spring Boot 2.1.13.RELEASE

## 目录结构

- bk-java
    - db sql文件
    - com.itplh.bk
        - mapper
            - HouseInfoMapper 
        - model
            - HouseInfo 房源信息实体对象
            - PageData
        - properties
            - BkSpiderProperties
        - service
            - BkService 核心业务类
    - resources
        - application.properties

## 快速开始

1. 在`application.properties`中设置需要抓取数据的目标房源链接 
    ```properties
    bk.spider.index-url=https://cq.ke.com/ershoufang/ranjiaba/
    ```
2. 设置数据库连接信息
3. 在`com.itplh.bk.model.HouseInfo`中，使用`@TableName("house_info")`指定数据保存到哪张表，默认保存到`house_info`
4. 启动`com.itplh.bk.Application`

**小提示**
- 贝壳最多只显示100页的数据，也就是说最好目标链接的房源是低于`100*30=3000`个
- 贝壳携带的查询参数是拼接在path中，并非表单提交的形式
- `spring.profiles.active=dev`只抓取首页数据

## 项目依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.62</version>
    </dependency>
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.11.2</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.2.0</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.17</version>
    </dependency>
</dependencies>
```
