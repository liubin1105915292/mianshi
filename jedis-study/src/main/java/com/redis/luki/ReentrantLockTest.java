package com.redis.luki;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * auther win
 * create 2021/4/13 0013 20:35
 **/
public class ReentrantLockTest {
    private static final Lock lock = new ReentrantLock();

    public static void sm(){
        lock.lock();
        for (int i = 0; i < 30; i++) {
            System.out.println(Thread.currentThread().getName()+": "+i);
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        Runnable ab = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        new Thread(ab).start();
        new Thread(ab).start();
        new Thread(ab).start();
    }
}

