package com.itplh.integration.flow;

import com.itplh.integration.gateway.SendingGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.mail.dsl.Mail;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author: tanpenggood
 * @date: 2020-10-25 22:42
 */
@Configuration
public class IntegrationConfig {

    /**
     * 主集成流程定义
     * 定义IntegrationFlow的Bean定义集成流程
     *
     * @return
     */
    @Bean
    IntegrationFlow flowFormGateway() {
        // 通过from()方法，从Gateway开始集成流程。通过已经获得的IntegrationFlowBuilder的方法来构建IntegrationFlow
        return IntegrationFlows.from(SendingGateway.class)
                // 通过filter()方法过滤掉非h开头的字符，<String>指的是当前消息的类型。当前端点到下一端点Transformer之间的通道可省略
                .<String>filter(payload -> payload.startsWith("h"))
                // 通过transform()来转换消息，在所有的消息后加!。<String, String>第一个String是原消息类型，第二个String是转换后的消息类型
                .<String, String>transform(payload -> payload + "!")
                // 通过route()方法将消息路由到不同的通道，第一个参数为路由条件
                .<String, Boolean>route(payload -> payload.startsWith("hello"),
                        // 把以hello开头的消息发送到fileChannel通道
                        mapping -> mapping.channelMapping(true, "fileChannel")
                                // 把不以hello开头的消息发送到emailChannel通道
                                .channelMapping(false, "emailChannel"))
                // 通过get方法获得IntegrationFlow
                .get();
    }

    /**
     * fileChannel
     * 写文件流程定义
     *
     * @return
     */
    @Bean
    IntegrationFlow flowFormChannelToFile() {
        // 写文件集成流程从fileChannel通道读取消息
        return IntegrationFlows.from("fileChannel")
                // 通过handle()方法调用OutboundChannelAdapter。通过Files.outboundAdapter()的构造，将消息输出到文件，构造的参数为指定文件输出的位置
                .handle(Files.outboundAdapter(new File("/Users/tanpeng/file"))
                        // 如果文件存在，则消息会附加到文件尾部
                        .fileExistsMode(FileExistsMode.APPEND)
                        // 设置附加到文件的消息会另起一行
                        .appendNewLine(true))
                .get();
    }

    /**
     * emailChannel
     * 发邮件流程定义
     *
     * @return
     */
    @Bean
    IntegrationFlow flowFormChannelToMail() {
        // 发邮件流程从emailChannel通道读取消息
        return IntegrationFlows.from("emailChannel")
                // 可以通过enrichHeaders()方法丰富头信息，通过Mail.headers()来设置头信息
                .enrichHeaders(Mail.headers()
                        // 指定邮件主题
                        .subject("来自learning-spring-integration的信息")
                        // 指定邮件发送人
                        .from("tanpengswpu@163.com")
                        // 指定邮件接收人
                        .to("tanpengswpu@163.com"))
                // 通过handle()方法调用OutboundChannelAdapter。此处设置通过SMTP来发送邮件，通过Mail.outboundAdapter()来构造，参数为服务器的地址
                .handle(Mail.outboundAdapter("smtp.163.com")
                        // 指定服务器端口
                        .port(25)
                        // 指定协议
                        .protocol("smtp")
                        // 指定发送者的账号密码
                        .credentials("tanpengswpu@163.com", "******"))
                .get();
    }

    /**
     * 拷贝文件流程定义
     * 这是一个新的流程，和上面的流程没有关系，这个流程将/Users/tanpeng/file文件夹中的txt文件拷贝到/Users/tanpeng/output
     *
     * @return
     */
    @Bean
    IntegrationFlow copyFileFlow() {
        // from()方法从InboundChannelAdapter开始。Files.inboundAdapter()方法指定需读去的文件，匹配模式为所有的txt文件
        return IntegrationFlows.from(Files.inboundAdapter(new File("/Users/tanpeng/file")).patternFilter("*.txt"),
                // from()方法的第二个参数为每隔5秒轮询这个文件夹
                c -> c.poller(Pollers.fixedRate(5, TimeUnit.SECONDS)))
                // 通过enrichHeaders()来丰富头信息，通过FileHeaders.FILENAME修改文件名
                .enrichHeaders(h -> h.header(FileHeaders.FILENAME, "copy.txt", true))
                // 通过handle()方法调用Files.outboundAdapter()，将文件输出到/Users/tanpeng/output文件夹。若文件存在，则替换文件
                .handle(Files.outboundAdapter(new File("/Users/tanpeng/output"))
                        .fileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED))
                .get();
    }
}
