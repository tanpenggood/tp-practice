package com.itplh.chapter5.section2_learn_web_mvc.point3_controller_advice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tanpenggood
 * @since: 2020-06-20 01:19
 */
@RestController
@RequestMapping("/chapter5/section2/point3/usage1")
public class Usage1PersonNameNotFoundController {

    @GetMapping
    public void exception(String name) {
        throw new Usage1PersonNameNotFoundException(name);
    }
}
