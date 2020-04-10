## my-spring-boot-starter

> 学习如何编写自己的 `spring-boot-starter-xxx`

## 目录结构
- my-spring-boot-starter
    1. myapp-spring-boot-autoconfigure
    2. myapp-spring-boot-starter `依赖1`
    3. myapp-spring-boot-starter-test `依赖2` 测试工程


## 编写一个spring-boot-starter的步骤
   
- 创建名字为 `xxx-spring-boot-starter` 的启动器项目
- 创建名字为 `xxx-spring-boot-autoconfigure`的项目
    - 编写属性绑定类 `xxxProperties`
    - 编写服务类 `xxxService`，并引入 `xxxProperties`
    - 编写自动配置类`xxxAutoConfiguration` 注入配置
    - 创建 `spring.factories` 文件，用于指定要自动配置的类

> 启动器项目为空项目，用来引入 `xxx-spring-boot-autoconfigure`等其他依赖

## 使用自定义的spring-boot-starter
- 项目引入 `xxx-spring-boot-starter`
- 配置需要配置的信息

## 参考

[Springboot 系列（十五）如何编写自己的 Springboot starter](https://blog.csdn.net/u013735734/article/details/102858847)

[简书 - 自定义spring-boot-starter](https://www.jianshu.com/p/1d547c7a5670)
