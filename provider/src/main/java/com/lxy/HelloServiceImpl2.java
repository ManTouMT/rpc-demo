package com.lxy;

public class HelloServiceImpl2 implements HelloService{
    @Override
    public void sayHello(String something) {
        System.out.println("I'm v2.Hello," + something);
    }
}
