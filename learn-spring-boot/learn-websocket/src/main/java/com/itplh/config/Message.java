package com.itplh.config;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-20 11:39
 * @version: 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    public static final String ENTER = "ENTER";
    public static final String SPEAK = "SPEAK";
    public static final String QUIT = "QUIT";

    private String type;//消息类型
    private String username; //发送人
    private String msg; //发送消息
    private int onlineCount; //在线用户数

    public static String jsonStr(String type, String username, String message, int onlineTotal) {
        return JSON.toJSONString(new Message(type, username, message, onlineTotal));
    }

}
