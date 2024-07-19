package com.lxy;

import com.lxy.protocol.HttpClient;
import common.Invocation;
import proxy.ProxyFactory;

public class Main {
    public static void main(String[] args) {
        // 修改之前的调用方式
        Invocation invocation = new Invocation(
                HelloService.class.getName(),
                "sayHello", 
                new Object[]{"lxy"},
                new Class[]{String.class});
        //        HttpClient httpClient = new HttpClient();
        //        String result = httpClient.send("localhost", 8080, invocation);
        
        HelloService proxy = ProxyFactory.getProxy(HelloService.class);
        String result = proxy.sayHello("21321321");
        System.out.println(result);
    }
}
