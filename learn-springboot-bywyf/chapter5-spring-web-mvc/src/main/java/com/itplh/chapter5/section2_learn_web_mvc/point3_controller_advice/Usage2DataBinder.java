package com.itplh.chapter5.section2_learn_web_mvc.point3_controller_advice;

import com.itplh.chapter5.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author: tanpenggood
 * @since: 2020-06-20 01:13
 */
@Slf4j
@ControllerAdvice
public class Usage2DataBinder {

    @InitBinder
    public void registerPersonEditor(WebDataBinder binder,
                                     String person) {
        log.info("在initBinder中为字符串 {}", person);
        binder.registerCustomEditor(Person.class, new Usage2PersonEditor());
    }

}
