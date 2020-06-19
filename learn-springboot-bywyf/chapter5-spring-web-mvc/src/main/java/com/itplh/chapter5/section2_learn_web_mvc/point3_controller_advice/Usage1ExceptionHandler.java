package com.itplh.chapter5.section2_learn_web_mvc.point3_controller_advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author: tanpenggood
 * @since: 2020-06-20 01:13
 */
@ControllerAdvice
public class Usage1ExceptionHandler {

    @ExceptionHandler(Usage1PersonNameNotFoundException.class)
    public ResponseEntity<String> customExceptionHandler(Usage1PersonNameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getName() + "没有找到！");
    }

}
