package cn.laomo.thread1.t1_001;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @program: MultThreadDemos
 * @description: 通过继承线程类
 * @author: 老马
 * @create: 2020-01-16 10:20
 **/
public class MyThread extends Thread {
    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(String.format("MyThread执行次数:%d", i));
        }
    }
}
