package com.itplh.bk.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-11 14:21
 * @version: 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "bk.spider")
@EnableConfigurationProperties(BkSpiderProperties.class)
public class BkSpiderProperties {
    private String indexUrl = defaultIndexUrl();
    private String pathParam = defaultPathParam();
    private String pageUrlTemplate = defaultPageUrlTemplate();

    private String defaultIndexUrl() {
        return "https://cq.ke.com/ershoufang";
    }

    private String defaultPathParam() {
        return "";
    }

    private String defaultPageUrlTemplate() {
        return String.join("/", defaultIndexUrl(), "pg%s" + defaultPathParam());
    }

}
