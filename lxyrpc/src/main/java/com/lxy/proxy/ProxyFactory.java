package com.lxy.proxy;

import com.lxy.common.Invocation;
import com.lxy.common.URL;
import com.lxy.loadbalance.RandomLoadBalance;
import com.lxy.protocol.HttpClient;
import com.lxy.register.RemoteRegister;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProxyFactory {
    public static <T> T getProxy(Class<T> interfaceClass) {
        // 同样可以读取用户配置

        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                (proxy, method, args) -> {
                    Invocation invocation = new Invocation(interfaceClass.getName(),
                            method.getName(),
                            args,
                            method.getParameterTypes(),
                            "2");
                    HttpClient httpClient = new HttpClient();
                    
                    // 已经调用过的机器
                    List<URL> invokedUrl = new ArrayList<>();
                    // 重试 todo 也可以做成配置
                    int maxRetryCount = 3;
                    while (maxRetryCount > 0) {
                        // 服务发现
                        List<URL> urls = RemoteRegister.get(interfaceClass.getName());
                        // 已经调用过得不再调用
                        urls.removeAll(invokedUrl);
                        
                        // 负载均衡
                        URL random = RandomLoadBalance.random(urls);

                        // 服务调用
                        try {
                            // 服务调用
                            return httpClient.send(random.getHostname(), random.getPort(), invocation);
                        } catch (Exception e) {
                            invokedUrl.add(random);
                            maxRetryCount--;
                            // todo 容错处理 去调配置中的回调方法errorCallback
                            // 配置错误回调：error-callback=com.lxy.ErrorCallbackHelloServiceImpl
                        } 
                    }
                    return "服务调用出错，已用尽最大重试次数";
                });
        return (T) proxyInstance;
    }
}
