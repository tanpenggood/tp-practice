package com.aden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * 首先指定服务注册中心的地址为http://localhost:8761/eureka/，服务的端口为8888，服务名为service-zuul
 * 以/api-a/ 开头的请求都转发给service-ribbon服务；以/api-b/开头的请求都转发给service-feign服务
 *
 * 依次运行(注册中心、service-hi、service-ribbon、service-feign、service-zuul)这五个工程
 * 打开浏览器访问：http://localhost:8888/api-a/hi?name=tanpeng ;浏览器显示：
 *  hi tanpeng,i am from port:8762
 * 打开浏览器访问：http://localhost:8888/api-b/hi?name=tanpeng ;浏览器显示：
 *  hi tanpeng,i am from port:8762
 * 这说明zuul起到了路由的作用
 *
 * zuul不仅只是路由，并且还能过滤，做一些安全验证，我们添加MyFilter之后
 * 访问：http://localhost:8888/api-a/hi?name=tanpeng ；网页显示：
 *  token is empty
 * 访问 http://localhost:8888/api-a/hi?name=tanpeng&token=22 ； 网页显示：
 *  hi tanpeng,i am from port:8762
*  说明zuul的过滤也配置好了
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ServiceZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceZuulApplication.class, args);
    }

}
