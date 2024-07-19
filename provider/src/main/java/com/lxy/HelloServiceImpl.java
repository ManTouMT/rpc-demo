package com.lxy;

public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String something) {
        return "Hello," + something;
    }
}
