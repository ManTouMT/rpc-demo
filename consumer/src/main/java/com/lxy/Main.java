package com.lxy;

import com.lxy.protocol.HttpClient;
import common.Invocation;

public class Main {
    public static void main(String[] args) {
        Invocation invocation = new Invocation(
                HelloService.class.getName(),
                "sayHello", 
                new Object[]{"lxy"},
                new Class[]{String.class});
        HttpClient httpClient = new HttpClient();
        String result = httpClient.send(
                "localhost", 
                8080, 
                invocation);
        System.out.println(result);
    }
}
