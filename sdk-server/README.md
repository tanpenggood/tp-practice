## sdk-server

让天下没有难对的接口。

如果有，那就让我来！

## 工程结构

```
sdk-server     # 父工程(pom)
    sdk-boot       # 工程启动主类(jar)
    sdk-common     # 通用/配置模块
    wx-sdk         # 微信SDK模块
    ...
    xxx-sdk        # 其他SDK模块
```

## 启动主类

`com.itplh.sdk.boot.Main`

## 技术架构

- Java8
- Spring Boot 2.1.13.RELEASE
- mybatis-plus 3.0.6
- h2 1.4.200
- lombok 1.18.12