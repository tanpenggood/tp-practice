package com.itplh.web.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-20 12:45
 * @version: v1.0.0
 */
@Component
@Data
public class DeferredResultHolder {

    private Map<String, DeferredResult<Map>> completeOrder = new HashMap<>(2);

}
