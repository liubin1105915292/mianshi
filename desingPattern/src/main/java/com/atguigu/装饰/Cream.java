package com.atguigu.装饰;

/**
 * auther win
 * create 2021/4/18 0018 22:57
 **/
public class Cream extends Food {
    private Food basic_food;

    public Cream(Food basic_food) {
        this.basic_food = basic_food;
    }
    public String make(){
        return basic_food.make()+"+奶油";
    }
}
