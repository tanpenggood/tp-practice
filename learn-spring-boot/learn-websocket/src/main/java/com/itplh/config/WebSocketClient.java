package com.itplh.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.websocket.Session;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-20 13:39
 * @version: 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketClient {

    private Session session;
    private String username;

}
