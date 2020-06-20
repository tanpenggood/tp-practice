package com.itplh.chapter5.section2_learn_web_mvc.point4_rest_controller_advice;

import com.itplh.chapter5.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Random;

/**
 * @author: tanpenggood
 * @since: 2020-06-20 22:50
 */
@Slf4j
@RestControllerAdvice
public class Usage1CustomRequestBodyAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasParameterAnnotation(Usage1ProcessTag.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (body instanceof Person) {
            Person person = (Person) body;
            String upperCaseName = person.getName().toUpperCase();
            person.setName(upperCaseName);
            log.info("Usage1CustomRequestBodyAdvice#afterBodyRead {}", person.toString());
            return person;
        }
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (Person.class.isAssignableFrom((Class<?>) targetType)) {
            return new Person(new Random().nextLong(), "Nobody", -1);
        }
        return body;
    }
}
