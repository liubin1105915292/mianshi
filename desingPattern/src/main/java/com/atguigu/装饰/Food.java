package com.atguigu.装饰;

/**
 * auther win
 * create 2021/4/18 0018 22:51
 **/

/*对已有的业务逻辑进一步的封装，使其增加额外的功能，如Java中的IO流就使用了装饰者模式，用户在使用的时候，可以任意组装，达到自己想要的效果。
        举个栗子，我想吃三明治，首先我需要一根大大的香肠，我喜欢吃奶油，在香肠上面加一点奶油，再放一点蔬菜，最后再用两片面包夹一下，
        很丰盛的一顿午饭，营养又健康。（ps：不知道上海哪里有卖好吃的三明治的，求推荐～）那我们应该怎么来写代码呢？
        首先，我们需要写一个Food类，让其他所有食物都来继承这个类，看代码：*/
public class Food {
    public String foodName;
    public Food(){}

    public Food(String foodName) {
        this.foodName = foodName;
    }
     public String make(){
        return foodName;
     }
}
