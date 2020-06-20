package com.itplh.chapter5.section2_learn_web_mvc.point4_rest_controller_advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itplh.chapter5.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

/**
 * @author: tanpenggood
 * @since: 2020-06-20 23:11
 */
@Slf4j
@RestControllerAdvice
public class Usage1CustomResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.hasMethodAnnotation(Usage1ProcessTag.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof Person) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("person", body);
            map.put("extra-response-body", "demo-body");
            log.info("Usage1CustomResponseBodyAdvice#beforeBodyWrite {}", map);
            return map;
        }
        return body;
    }
}
