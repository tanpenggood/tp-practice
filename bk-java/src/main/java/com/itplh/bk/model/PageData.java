package com.itplh.bk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-11 00:32
 * @version: v1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageData {

    private int totalPage;
    private int curPage;

}
