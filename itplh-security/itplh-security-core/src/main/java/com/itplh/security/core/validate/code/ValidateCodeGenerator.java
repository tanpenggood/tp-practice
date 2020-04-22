package com.itplh.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-22 22:25
 * @version: v1.0.0
 */
public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request);

}
