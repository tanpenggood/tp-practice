package com.itplh.chapter5.section2_learn_web_mvc.point3_controller_advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: tanpenggood
 * @since: 2020-06-20 01:17
 */
@Getter
@AllArgsConstructor
public class Usage1PersonNameNotFoundException extends RuntimeException {

    private String name;

}
