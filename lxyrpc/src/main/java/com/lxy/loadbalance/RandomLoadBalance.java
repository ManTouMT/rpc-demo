package com.lxy.loadbalance;

import com.lxy.common.URL;

import java.util.List;
import java.util.Random;

public class RandomLoadBalance {

    public static URL random(List<URL> urls) {
        Random random = new Random();
        int index = random.nextInt(urls.size());
        return urls.get(index);
    }
}
