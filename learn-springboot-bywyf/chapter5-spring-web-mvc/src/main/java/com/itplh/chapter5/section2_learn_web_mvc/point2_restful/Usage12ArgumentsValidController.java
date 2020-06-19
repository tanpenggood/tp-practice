package com.itplh.chapter5.section2_learn_web_mvc.point2_restful;

import com.itplh.chapter5.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: tanpenggood
 * @since: 2020-06-20 00:56
 */
@Slf4j
@RestController
@RequestMapping("/chapter5/section2/point2/usage12")
public class Usage12ArgumentsValidController {

    @PostMapping
    public ResponseEntity validate(@Valid @RequestBody Person person,
                                           BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(result.getAllErrors());
        }
        return ResponseEntity.ok()
                .body("everything is fine");
    }

}