package register;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地注册（与之对应的就是服务注册，发现等组件）
 **/
public class LocalRegister {
    /**
     * key: 接口名
     * value：接口实现
     **/
    private final static Map<String, Class<?>> map = new HashMap<>();
    
    public static void register(String className, Class<?> clazz) {
        register(className, "", clazz);
    }

    /**
     * 可以接收一个版本号用以区分同一个接口的不同实现，也提供无版本号的默认实现
     **/
    public static void register(String className, String version, Class<?> clazz) {
        map.put(className + version, clazz);
    }
    
    public static Class<?> get(String className) {
        return get(className, "");
    }

    public static Class<?> get(String className, String version) {
        return map.get(className + version);
    }
    
}
