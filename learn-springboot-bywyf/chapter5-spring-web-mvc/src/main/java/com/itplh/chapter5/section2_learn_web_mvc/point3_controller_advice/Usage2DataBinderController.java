package com.itplh.chapter5.section2_learn_web_mvc.point3_controller_advice;

import com.itplh.chapter5.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tanpenggood
 * @since: 2020-06-20 01:19
 */
@Slf4j
@RestController
@RequestMapping("/chapter5/section2/point3/usage2")
public class Usage2DataBinderController {

    @GetMapping
    public Person propertyPerson(@RequestParam Person person) {
        log.info("通过InitBinder注册的PropertyEditor转换后对象为 {}", person);
        return person;
    }
}
