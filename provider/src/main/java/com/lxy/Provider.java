package com.lxy;

import com.lxy.protocol.HttpServer;
import register.LocalRegister;

public class Provider {
    public static void main(String[] args) {
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        LocalRegister.register(HelloService.class.getName(), "2", HelloServiceImpl2.class);
        //接收网络请求：tomcat，jetty
        HttpServer httpServer = new HttpServer();
        // 从配置中获取hostname和port   来源有用户配置，nacos等等
        httpServer.start("localhost", 8080);
        
    }
}
