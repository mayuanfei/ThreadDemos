package cn.laoma.thread6.t6_001_Question;

import java.util.Arrays;
import java.util.List;

/**
 * @program: ThreadDemos
 * @description: 用wait、notify实现
 * @author: 老马
 * @create: 2021-10-03 23:17
 **/
public class T02_Question_WaitNotify {

    public static void main(String[] args) {
        final Object o = new Object();
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final List<String> strings = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

        new Thread(() -> {
            synchronized (o) {
                strings.forEach(item -> {
                    System.out.print(item);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                //这里注意循环外的这个notify
                o.notify();
            }
        }, "t2").start();

        new Thread(() -> {
            synchronized (o) {
                integers.forEach(item -> {
                    System.out.print(item);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                o.notify();
            }
        }, "t1").start();


    }
}