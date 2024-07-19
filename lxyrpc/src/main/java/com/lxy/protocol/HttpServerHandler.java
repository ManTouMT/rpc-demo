package com.lxy.protocol;

import common.Invocation;
import org.apache.commons.io.IOUtils;
import register.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 对请求的处理
 **/
public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        // 处理请求---》接口、方法、方法参数
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            // 调用的接口名
            String interfaceName = invocation.getInterfaceName();
            String version = invocation.getVersion() == null ? "" : invocation.getVersion();
            /**
             * 怎么通过接口名快速找到实现类：本地注册 
             * @see register.LocalRegister
             **/
            Class<?> classImpl = LocalRegister.get(interfaceName, version);
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            method.setAccessible(true);
            String result = (String) method.invoke(classImpl, invocation.getParameters());
            // 写入结果
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }

    }
}
