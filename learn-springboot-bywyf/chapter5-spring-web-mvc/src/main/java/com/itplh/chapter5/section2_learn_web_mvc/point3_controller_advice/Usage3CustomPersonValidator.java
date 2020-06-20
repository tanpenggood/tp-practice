package com.itplh.chapter5.section2_learn_web_mvc.point3_controller_advice;


import com.itplh.chapter5.pojo.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 实现org.springframework.validation.Validator来自定义校验
 *
 * @author: tanpenggood
 * @since: 2020-06-20 13:38
 */
public class Usage3CustomPersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        // 只支持Person类
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        validateId(person, errors);
        validateName(person, errors);
        validateAge(person, errors);
    }

    private void validateId(Person person, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "id", "CustomPersonValidator.id", "id不能为空-自定义");
    }

    private void validateName(Person person, Errors errors) {
        int nameLength = person.getName().length();
        if (nameLength < 3 || nameLength > 5) {
            errors.rejectValue("name", "CustomPersonValidator.name", "name在3到5个字符之间-自定义");
        }
    }

    private void validateAge(Person person, Errors errors) {
        if (person.getAge() < 18) {
            errors.rejectValue("age", "CustomPersonValidator.age", "age不能低于18岁-自定义");
        }
    }

}
