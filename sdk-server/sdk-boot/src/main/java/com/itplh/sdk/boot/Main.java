package com.itplh.sdk.boot;

import com.itplh.sdk.SdkServerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(SdkServerApplication.class, args);
        ConfigurableEnvironment env = context.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        System.out.println("##########################################################################");
        System.out.println("# SDK SERVER is running! Access URLs:");
        System.out.printf("Local: http://localhost:%s\n", port);
        System.out.printf("External: http://%s:%s\n", ip, port);
        System.out.println("\n# database about:");
        System.out.printf("db url: %s\n", env.getProperty("spring.datasource.url"));
        System.out.printf("db username: %s\n", env.getProperty("spring.datasource.username"));
        System.out.printf("db password: %s\n", env.getProperty("spring.datasource.password"));
        System.out.printf("H2 Console: http://localhost:%s%s\n", port, env.getProperty("spring.h2.console.path"));
        System.out.println("##########################################################################");
    }

}
