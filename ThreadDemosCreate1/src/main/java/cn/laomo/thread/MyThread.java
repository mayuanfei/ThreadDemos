package cn.laomo.thread;

import java.util.Date;

/**
 * @program: MultThreadDemos
 * @description: 通过继承线程类
 * @author: 老马
 * @create: 2020-01-16 10:20
 **/
public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("MyThread执行:%d", System.currentTimeMillis()));
        }
    }
}
