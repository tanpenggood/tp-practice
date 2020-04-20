package com.itplh.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-04-13 14:50
 * @version: 1.0.0
 */
@Slf4j
@Component
@ServerEndpoint("/chat/{username}")
public class WebSocketServerEndpoint {
    /**
     * 全部在线会话  PS: 基于场景考虑 这里使用线程安全的Map存储会话对象。
     */
    private static Map<String, WebSocketClient> onlineClients = new ConcurrentHashMap<>();

    /**
     * 当客户端打开连接：1.添加会话对象 2.更新在线人数
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "username") String username) {
        WebSocketClient client = WebSocketClient.builder().session(session).username(username).build();
        onlineClients.put(session.getId(), client);
        sendMessageToAll(Message.jsonStr(Message.ENTER, username, "上线了！", onlineClients.size()));
    }

    /**
     * 当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
     * <p>
     * PS: 这里约定传递的消息为JSON字符串 方便传递更多参数！
     */
    @OnMessage
    public void onMessage(String jsonStr) {
        Message message = JSON.parseObject(jsonStr, Message.class);
        sendMessageToAll(Message.jsonStr(Message.SPEAK, message.getUsername(), message.getMsg(), onlineClients.size()));
        log.info("---------------------onMessage---------------------");
        log.info("jsonStr {}", jsonStr);
        log.info("onlineClients key {}, ", onlineClients.keySet());
        log.info("onlineClients username {}, ", onlineClients.values().stream().map(item -> item.getUsername()).collect(Collectors.toList()));
        log.info("---------------------onMessage---------------------");
    }

    /**
     * 当关闭连接：1.移除会话对象 2.更新在线人数
     */
    @OnClose
    public void onClose(Session session, @PathParam(value = "username") String username) {
        onlineClients.remove(session.getId());
        sendMessageToAll(Message.jsonStr(Message.QUIT, username, "下线了！", onlineClients.size()));
    }

    /**
     * 当通信发生异常：打印错误日志
     */
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    /**
     * 广播消息(发送信息给所有人)
     */
    private static void sendMessageToAll(String message) {
        onlineClients.forEach((sessionId, client) -> {
            try {
                client.getSession().getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * p2p(发送信息给指定用户)
     */
    private static void sendMessageTo(String message, String toUserName) {
        for (WebSocketClient client: onlineClients.values()) {
            if (client.getUsername().equals(toUserName)) {
                try {
                    client.getSession().getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
