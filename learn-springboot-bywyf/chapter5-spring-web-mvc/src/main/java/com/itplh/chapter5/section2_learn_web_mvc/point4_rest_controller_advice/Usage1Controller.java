package com.itplh.chapter5.section2_learn_web_mvc.point4_rest_controller_advice;

import com.itplh.chapter5.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tanpenggood
 * @since: 2020-06-20 23:13
 */
@Slf4j
@RestController
@RequestMapping("/chapter5/section2/point4/usage1")
public class Usage1Controller {

    @GetMapping
    @Usage1ProcessTag
    public Person modifyRequestBody(@Usage1ProcessTag @RequestBody Person person) {
        log.info("Usage1Controller#modifyRequestBody {}", person.toString());
        return person;
    }
}
