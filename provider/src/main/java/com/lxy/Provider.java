package com.lxy;

import com.lxy.protocol.HttpServer;
import com.lxy.common.URL;
import com.lxy.register.LocalRegister;
import com.lxy.register.RemoteRegister;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Provider {
    // 可以做成配置
    private static final int SERVER_PORT = 8080;
    public static void main(String[] args) {
        // 本地注册
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        LocalRegister.register(HelloService.class.getName(), "2", HelloServiceImpl2.class);
        
        // 注册中心注册
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String hostAddress = localHost.getHostAddress();
        URL url = new URL(hostAddress, SERVER_PORT);
        // 服务注册
        RemoteRegister.register(HelloService.class.getName(), url);
        
        //接收网络请求：tomcat，jetty
        HttpServer httpServer = new HttpServer();
        // 从配置中获取hostname和port   来源有用户配置，nacos等等
        httpServer.start(hostAddress, SERVER_PORT);
        
    }
}
