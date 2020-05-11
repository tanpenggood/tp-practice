package com.itplh.bk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-10 23:40
 * @version: v1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfo {

    private String maidian;
    private String title;
    private String href;
    private String neighbourhood;
    private String floor;
    private String buildYear;
    private String type;
    private String size;
    private String faceToward;
    private String focus;
    private String publishTime;
    private String totalPrice;
    private String unitPrice;

}
