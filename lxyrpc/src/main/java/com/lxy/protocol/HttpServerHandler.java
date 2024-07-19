package com.lxy.protocol;

import common.Invocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 类似责任链模式
 **/
public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        // 处理请求---》接口、方法、方法参数
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
