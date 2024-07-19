package com.lxy;

public class HelloServiceImpl2 implements HelloService{
    @Override
    public String sayHello(String something) {
        return "I'm v2.Hello," + something;
    }
}
