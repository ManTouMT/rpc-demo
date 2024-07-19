package com.lxy.register;

import com.lxy.common.URL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册中心三要素：  数据共享机制（需要引入第三方如redis等来实现），心跳机制，数据变更的更新机制
 * 本次demo数据共享通过读写文件registrationInfo来实现
 * todo 服务挂掉的处理（心跳）
 **/
public class RemoteRegister {
    /**
     * key: 接口名
     * value：提供服务的机器的地址（可能是集群，所以是list）
     **/
    private static Map<String, List<URL>> registrationMap = new HashMap<>();

    public static void register(String interfaceName, URL url) {
        List<URL> list = registrationMap.computeIfAbsent(interfaceName, k -> new ArrayList<>());
        list.add(url);
        saveRegistrationInfo();
    }

    public static List<URL> get(String interfaceName) {
        registrationMap = extractRegistrationInfo();
        return registrationMap.get(interfaceName);
    }
    
    public static void saveRegistrationInfo() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/registrationInfo");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(registrationMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Map<String, List<URL>> extractRegistrationInfo() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/registrationInfo");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
