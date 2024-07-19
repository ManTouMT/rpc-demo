package com.lxy.proxy;

import com.lxy.common.Invocation;
import com.lxy.common.URL;
import com.lxy.loadbalance.RandomLoadBalance;
import com.lxy.protocol.HttpClient;
import com.lxy.register.RemoteRegister;

import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {
    public static <T> T getProxy(Class<T> interfaceClass) {
        // 同样可以读取用户配置，按照用户想

        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                (proxy, method, args) -> {
                    Invocation invocation = new Invocation(interfaceClass.getName(),
                            method.getName(),
                            args,
                            method.getParameterTypes());
                    HttpClient httpClient = new HttpClient();
                    // 服务发现
                    List<URL> urls = RemoteRegister.get(interfaceClass.getName());
                    // 负载均衡
                    URL random = RandomLoadBalance.random(urls);
                    //服务调用
                    try {
                        return httpClient.send(random.getHostname(), random.getPort(), invocation);
                    } catch (Exception e) {
                        // todo 容错处理 去调配置中的回调方法errorCallback
                        // 配置错误回调：error-callback=com.lxy.ErrorCallbackHelloServiceImpl
                        return "服务调用出错";
                    }
                });
        return (T) proxyInstance;
    }
}
