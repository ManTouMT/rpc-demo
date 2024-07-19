package com.lxy;

public class ErrorCallbackHelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String something) {
        return "服务出错了" + something;
    }
}
