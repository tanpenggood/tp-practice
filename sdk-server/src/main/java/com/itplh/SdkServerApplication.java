package com.itplh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class SdkServerApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(SdkServerApplication.class, args);
        ConfigurableEnvironment env = context.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        System.out.println("##########################################################################");
        System.out.println("# SDK SERVER is running! Access URLs:");
        System.out.printf("# Local: http://localhost:%s\n", port);
        System.out.printf("# External: http://%s:%s\n", ip, port);
        System.out.println("##########################################################################");
    }

}
