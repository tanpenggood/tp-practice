package com.itplh.web.async;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-20 13:29
 * @version: v1.0.0
 */
public class OrderApiInfo {
    private Map<String, String> orderApiMap;

    public Map<String, String> getOrderApiMap() {
        orderApiMap = new LinkedHashMap<>(6);
        orderApiMap.put("sync", "http://localhost:8080/order");
        orderApiMap.put("callable", "http://localhost:8080/order/callable");
        orderApiMap.put("deferred_result thread", "http://localhost:8080/order/deferred_result");
        orderApiMap.put("deferred_result thread_pool", "http://localhost:8080/order/deferred_result/thread_pool");
        return orderApiMap;
    }
}
