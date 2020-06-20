package com.itplh.chapter5.section2_learn_web_mvc.point3_controller_advice;

import com.itplh.chapter5.pojo.Person;
import com.itplh.chapter5.section2_learn_web_mvc.point2_restful.Usage12ArgumentsValidController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: tanpenggood
 * @since: 2020-06-20 01:19
 */
@Slf4j
@RestController
@RequestMapping("/chapter5/section2/point3/usage3")
public class Usage3CustomValidatorController {

    @Autowired
    private Usage12ArgumentsValidController usage12ArgumentsValidController;

    @PostMapping
    public ResponseEntity customPersonValidator(@Valid @RequestBody Person person,
                                                BindingResult result) {
        return usage12ArgumentsValidController.validate(person, result);
    }

}
