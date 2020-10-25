package com.itplh.integration.gateway;

import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Spring Integration 会在运行时自动生成接口的实现，send()方法的数据会传送到集成流程中。
 *
 * @author: tanpenggood
 * @date: 2020-10-25 22:37
 */
public interface SendingGateway {
    /**
     * @param filename 通过{@link Header}注解来指定文件名
     * @param content  消息
     */
    void send(@Header(FileHeaders.FILENAME) String filename, String content);
}
