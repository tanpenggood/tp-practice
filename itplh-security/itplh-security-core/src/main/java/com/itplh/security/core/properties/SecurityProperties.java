package com.itplh.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-22 12:49
 * @version: 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "itplh.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

}
