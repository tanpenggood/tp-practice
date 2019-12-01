package com.itplh.house.common.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: tanpeng
 * @date: 2019-12-01 16:49
 * @version: v1.0.0
 */
@Data
@Builder
public class User {
    private String sessionId;
    private String name;
    private Date loginTime;
}
