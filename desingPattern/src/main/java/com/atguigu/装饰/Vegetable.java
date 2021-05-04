package com.atguigu.装饰;

/**
 * auther win
 * create 2021/4/18 0018 22:58
 **/
public class Vegetable extends Food {
    private Food base_food;

    public Vegetable(Food base_food) {
        this.base_food = base_food;
    }
    public String make(){
        return base_food.make()+"+蔬菜";
    }
}
