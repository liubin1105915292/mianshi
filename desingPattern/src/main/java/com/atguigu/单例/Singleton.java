package com.atguigu.单例;

/**
 * auther win
 * create 2021/4/18 0018 22:37
 **/

public class Singleton {
    //懒汉式写法
   /* private static Singleton singleton;
    private Singleton(){}
    public static Singleton getInstance(){
        if (singleton==null){
            singleton = new Singleton();
        }
        return singleton;
    }*/

    //饿汉式
    private static Singleton singleton = new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return singleton;
    }


    //
}
