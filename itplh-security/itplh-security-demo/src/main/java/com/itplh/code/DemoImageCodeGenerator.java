package com.itplh.code;

import com.itplh.security.core.validate.code.ImageCode;
import com.itplh.security.core.validate.code.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description: 覆盖core中的图形验证码生成逻辑
 * @author: tanpeng
 * @date: 2020-04-22 22:39
 * @version: v1.0.0
 */
@Slf4j
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        log.info("更高级的图形验证码生成代码");
        return null;
    }
}
