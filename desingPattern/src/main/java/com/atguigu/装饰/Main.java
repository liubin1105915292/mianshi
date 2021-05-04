package com.atguigu.装饰;

/**
 * auther win
 * create 2021/4/18 0018 22:59
 **/
public class Main {
    public static void main(String[] args) {
        Food bread = new Bread(new Vegetable(new Cream(new Food("香肠"))));
        System.out.println(bread.make());
    }
}
