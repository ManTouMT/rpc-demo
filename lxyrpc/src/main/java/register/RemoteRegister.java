package register;

import common.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 远程注册中心
 **/
public class RemoteRegister {
    /**
     * key: 接口名
     * value：提供服务的机器的地址（可能是集群，所以是list）
     **/
    private final static Map<String, List<URL>> map = new HashMap<>();

    public static void register(String interfaceName, URL url) {
        List<URL> list = map.computeIfAbsent(interfaceName, k -> new ArrayList<>());
        list.add(url);
    }

    public static List<URL> get(String interfaceName) {
        return map.get(interfaceName);
    }
}
