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

1. 配置`application.properties` 
    - 目标链接，默认为：https://cq.ke.com/ershoufang
    - 查询参数，默认为空
    - 数据库相关配置
    ```properties
    # 目标链接(第一页), 可携带区域参数
    bk.spider.index-url=https://cq.ke.com/ershoufang
    #bk.spider.index-url=https://cq.ke.com/ershoufang/ranjiaba
    # 查询参数(价格 房型 建筑面积 房源特色 朝向 楼层 楼龄 装修 用途 电梯 权属 类型), 默认参数：必看好房
    bk.spider.path-param=tt9
    bk.spider.page-url-template=${bk.spider.index-url}/pg%s${bk.spider.path-param}/
    # 开发时打开，仅抓取首页数据
    #spring.profiles.active=dev
    
    spring.datasource.url=jdbc:mysql://local81:3306/bk_house?serverTimezone=GMT%2B8&characterEncoding=UTF-8&useUnicode=true&useSSL=false
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    ```
2. 启动`com.itplh.bk.Application`

**小提示**
- 贝壳最多只显示100页的数据，也就是说最好目标链接的房源是低于`100*30=3000`个
- 贝壳携带的查询参数是拼接在path中，并非表单提交的形式
- `spring.profiles.active=dev`仅抓取首页数据
- 可在`com.itplh.bk.model.HouseInfo`中，使用`@TableName("house_info")`指定数据保存到哪张表，默认保存到`house_info`


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
        <version>1.13.1</version>
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
