package com.itplh.starter;

/**
 * @description: HelloService是我们要提供给外部使用的bean
 * HelloAutoConfiguration包含了这个bean需要的信息
 * HelloAutoConfiguration负责提供这个bean
 * @author: tanpeng
 * @date: 2020-04-10 15:13
 * @version: 1.0.0
 */
public class HelloService {

    private HelloProperties helloProperties;

    public String sayHello(String name) {
        return "Hello " + name + "，" + helloProperties.getSuffix();
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

}
