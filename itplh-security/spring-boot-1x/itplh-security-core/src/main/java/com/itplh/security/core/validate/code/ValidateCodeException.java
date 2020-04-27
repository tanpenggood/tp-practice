package com.itplh.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-22 16:12
 * @version: 1.0.0
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
