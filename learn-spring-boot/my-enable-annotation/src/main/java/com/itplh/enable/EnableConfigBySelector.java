package com.itplh.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-11 11:01
 * @version: 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportSelector.class)
public @interface EnableConfigBySelector {
}
