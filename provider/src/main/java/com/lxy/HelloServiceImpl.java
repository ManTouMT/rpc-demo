package com.lxy;

public class HelloServiceImpl implements HelloService{
    @Override
    public void sayHello(String something) {
        System.out.println("Hello," + something);
    }
}
