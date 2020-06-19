package com.itplh.chapter5.section2_learn_web_mvc.point3_controller_advice;

import com.itplh.chapter5.pojo.Person;

import java.beans.PropertyEditorSupport;

/**
 * @author: tanpenggood
 * @since: 2020-06-20 01:29
 */
public class Usage2PersonEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] personStr = text.split("-");
        Long id = Long.valueOf(personStr[0]);
        String name = personStr[1];
        Integer age = Integer.valueOf(personStr[2]);
        super.setValue(new Person(id, name, age));
    }
}
