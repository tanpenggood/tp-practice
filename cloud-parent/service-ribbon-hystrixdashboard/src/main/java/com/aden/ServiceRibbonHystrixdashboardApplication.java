package com.aden;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
public class ServiceRibbonHystrixdashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonHystrixdashboardApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * springcloud Greenwich版本hystrix-dashboard: Unable to connect to Command Metric Stream解决办法
     * https://www.cnblogs.com/x1mercy/p/9291348.html
     *
     * 1、大神看源码发现要配置要配一个servlet Bean
     * 2、打开浏览器：访问http://localhost:8767/hystrix
     * 3、values输入：http://localhost:8767/actuator/hystrix.stream，Title可随便输入
     * 4、点击 Monitor Stream，进入监控界面
     * 5、访问：http://localhost:8764/hi?name=tanpeng
     * 6、再进入监控界面，查看
     *
     * 在feign中配置 Hystrix Dashboard 与这里在ribbon中配置是一样的步骤
     * 1、添加起步依赖 spring-boot-starter-actuator、spring-cloud-starter-netflix-hystrix-dashboard
     * 2、添加注解 @EnableHystrixDashboard
     * 3、配置ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
