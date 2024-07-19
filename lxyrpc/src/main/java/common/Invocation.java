package common;

import java.io.Serializable;

public class Invocation implements Serializable {
    /**
     * 接口名
     **/
    private String interfaceName;
    /**
     * 调用的方法名
     **/
    private String methodName;
    /**
     * 方法的参数
     **/
    private Object[] parameters;
    /**
     * 参数的类型
     **/
    private Class<?>[] parameterTypes;

    public Invocation(String interfaceName, String methodName, Object[] parameters, Class<?>[] parameterTypes) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameters = parameters;
        this.parameterTypes = parameterTypes;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
