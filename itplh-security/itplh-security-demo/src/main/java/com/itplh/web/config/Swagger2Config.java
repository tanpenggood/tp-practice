package com.itplh.web.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-20 23:30
 * @version: v1.0.0
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger Petstore")
                .description("This is a sample server Petstore server.  You can find out more about     Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).      For this sample, you can use the api key `special-key` to test the authorization     filters.")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("tanpeng","", "tpswpu@yeah.net"))
                .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // 方式一：单包扫描，此包路径下的类，才生成接口文档
//				.apis(RequestHandlerSelectors.basePackage("io.swagger.api"))
                // 方式二、多包扫描
                .apis(basePackage(
                        new StringBuilder()
                                .append("com.itplh.web.controller;")
                                .append("com.itplh.web.async;")
                                .toString()
                ))
                // 方式三、指定注解扫描，加了ApiOperation注解的类，才生成接口文档
//	            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .directModelSubstitute(LocalDate.class, Date.class)
                .directModelSubstitute(OffsetDateTime.class, Date.class)
                .apiInfo(apiInfo());
    }

    /** 定义分隔符,配置Swagger多包 */
    private static final String SPLITOR = ";";

    /**
     * 自定义basePackage方法，使swagger2能够实现多包扫描
     * @author  tanpeng
     * @date 2019/08/02
     * @param basePackage
     * @return com.google.common.base.Predicate<springfox.documentation.RequestHandler>
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(SPLITOR)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }
}
