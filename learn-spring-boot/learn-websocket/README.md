## learn-websocket

> spring boot 整合 websocket 打造在线聊天室

## 目录结构

- learn-websocket
    - src/main/java
        1. `com.itplh.config.Message` 消息类
        2. `com.itplh.config.WebSocketClient` 客户端(用户)
        3. `com.itplh.config.WebSocketConfig` ServerEndpointExporter配置类
        4. `com.itplh.config.WebSocketServerEndpoint` WebSocket服务类
        5. `com.itplh.controller.PageController` 视图转发器
        5. `com.itplh.WebSocketApplication` SpringBoot启动类
    - src/main/resources
        1. `templates/login.html` 登录页
        2. `templates/index.html` 聊天室主页
        3. `application.yml` SpringBoot配置文件

## 快速开始

1. 启动`com.itplh.WebSocketApplication`
2. 访问 `http://localhost:9999`

## 参考

[使用 WebSocket 打造在线聊天室（基于注解）](https://www.jianshu.com/p/55cfc9fcb69e)

[Spring Boot整合websocket实现群聊，点对点聊天](https://blog.csdn.net/qq_38455201/article/details/80374712)
