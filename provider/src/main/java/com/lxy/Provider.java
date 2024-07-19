package com.lxy;

import com.lxy.protocol.HttpServer;

public class Provider {
    public static void main(String[] args) {
        //接收网络请求：tomcat，jetty
        HttpServer httpServer = new HttpServer();
        // 从配置中获取hostname和port   来源有用户配置，nacos等等
        httpServer.start("localhost", 8080);
        
    }
}
