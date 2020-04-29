package com.itplh.web.async;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-20 13:29
 * @version: v1.0.0
 */
@Component
public class OrderApiInfo {
    private Map<String, String> orderApiMap;

    @Value("${server.port:8080}")
    private String port;

    public Map<String, String> getOrderApiMap() {
        orderApiMap = new LinkedHashMap<>(6);
        orderApiMap.put("sync", getOrderURI("/"));
        orderApiMap.put("sync callable", getOrderURI("/callable"));
        orderApiMap.put("async callable", getOrderURI("/callable-async"));
        orderApiMap.put("async deferred-result", getOrderURI("/deferred-result"));
        orderApiMap.put("async deferred-result thread-pool", getOrderURI("/deferred-result/thread-pool"));
        orderApiMap.put("async completion-stage", getOrderURI("/completion-stage"));
        orderApiMap.put("async completion-stage thread-pool", getOrderURI("/completion-stage/thread-pool"));
        return orderApiMap;
    }

    private String getOrderURI(String path) {
        return String.format("http://localhost:%s/order%s", port, path);
    }
}
