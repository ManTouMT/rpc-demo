package proxy;

import com.lxy.protocol.HttpClient;
import common.Invocation;
import common.URL;
import register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {
    public static <T> T getProxy(Class<T> interfaceClass) {
        // 同样可以读取用户配置，按照用户想

        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Invocation invocation = new Invocation(interfaceClass.getName(),
                                method.getName(),
                                args,
                                method.getParameterTypes());
                        HttpClient httpClient = new HttpClient();
                        // 服务发现
                        List<URL> urls = RemoteRegister.get(interfaceClass.getName());
                        // 负载均衡
                        return httpClient.send("localhost", 8080, invocation);
                    }
                });
        return (T) proxyInstance;
    }
}
