package com.luki.test;

import com.sun.glass.ui.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * auther win
 * create 2021/5/13 0013 20:44
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test {
    @Value("${config.url}")
    private String url;

    @Value("${config.name}")
    private String name;

    @org.junit.Test
    public void test(){
        System.out.println(url);
        System.out.println(name);
    }
}
