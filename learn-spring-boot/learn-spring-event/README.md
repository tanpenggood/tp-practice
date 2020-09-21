# learn-spring-event

> 学习 spring 中的`ApplicationEvent`

## 目录结构

- learn-spring-event
    - com.itplh
        - event 
            - CustomEvent 自定义事件
            - CustomEventListener 自定义事件监听器
        - EventApplication 启动类 (含触发自定义事件的接口)
        - Result

## 快速开始

1. 启动`com.itplh.EventApplication`
2. 访问 `http://localhost:8080/tigger-event`

## 项目依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```
