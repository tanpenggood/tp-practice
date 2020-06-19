package com.itplh.chapter5.section2_learn_web_mvc.point2_restful;

import com.itplh.chapter5.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author: tanpenggood
 * @since: 2020-06-19 22:43
 */
@Slf4j
@RestController
@RequestMapping("/chapter5/section2/point2/usage10")
public class Usage10MethodArgumentsController {

    @GetMapping
    public void methodArguments(WebRequest request,
                                NativeWebRequest nativeWebRequest,
                                ServletRequest servletRequest,
                                ServletResponse servletResponse,
                                HttpSession httpSession,
                                Locale locale,
                                TimeZone timeZone,
                                ZoneId zoneId,
                                Person person) {
        log.info("通用Web请求接口 {}", request);
        log.info("继承WebRequest，通用的请求和返回接口 {}", nativeWebRequest);
        log.info("Servlet请求 {}", servletRequest);
        log.info("Servlet返回 {}", servletResponse);
        log.info("HTTP会话 {}", httpSession);
        log.info("本地信息 {}", locale);
        log.info("时区信息 {}", timeZone);
        log.info("时区ID {}", zoneId);
        log.info("新建一个对象（非注入Bean） {}", person.toString());
    }
}
