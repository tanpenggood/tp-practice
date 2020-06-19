
在Spring Boot下，外部属性的加载顺序是，

先列的属性配置优先级最高

先列的配置属性可覆盖后列的配置属性

**优先级**⬇️

1. 命令行参数
2. SPRING_APPLICATION_JSON
3. ServletConfig 初始化参数
4. ServletContext 初始化参数
5. JNDI（java:comp/env） 
6. Java系统属性（System.getProperties()） 
7. 操作系统变量
8. RandomValuePropertySource随机值
9. 应用部署jar包外部的application-{profile}.properties/yml
10. 应用部署jar包内部的application-{profile}.properties/yml
11. 应用部署jar包外部的application.properties/yml
12. 应用部署jar包内部的application.properties/yml
13. @PropertySource
14. SpringApplication.serDefaultProperties()

**外部文件配置**
Sprint Boot会从以下位置加载外部配置文件application.properties/yml，
并读取成PropertySource加载到Environment
1. 入口类的当前目录的/config子目录
2. 入口类的当前目录
3. 类路径下的/config目录
4. 类路径的根目录