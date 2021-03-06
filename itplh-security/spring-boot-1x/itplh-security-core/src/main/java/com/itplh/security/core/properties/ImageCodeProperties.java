package com.itplh.security.core.properties;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-22 20:31
 * @version: v1.0.0
 */
@Data
public class ImageCodeProperties {

    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireIn = 60;
    private List<String> url = Collections.emptyList();

}
