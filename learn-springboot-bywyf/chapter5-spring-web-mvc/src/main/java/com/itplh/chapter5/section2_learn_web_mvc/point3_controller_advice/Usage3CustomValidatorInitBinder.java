package com.itplh.chapter5.section2_learn_web_mvc.point3_controller_advice;

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
public class Usage3CustomValidatorInitBinder {

    @InitBinder
    public void setPersonValidator(WebDataBinder binder) {
        binder.setValidator(new Usage3CustomPersonValidator());
    }

}
