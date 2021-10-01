package cn.laoma.thread5.t5_003_Concurrent_Container;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: ThreadDemos
 * @description: 写时复制。 用在读多写少的情况，读不加锁，写加锁
 * @author: 老马
 * @create: 2021-09-24 11:26
 **/
public class T02_CopyOnWriteArrayList {
    @SneakyThrows
    public static void main(String[] args) {
        //1.解释下写时复制的含义
        //2看下add和get的源码
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        //这里可以通过Collections.synchronizedList把一个线程不安全的ArrayList变为线程安全的
//        List<String> list = new ArrayList<>();
//        List<String> synlist = Collections.synchronizedList(list);
        //写线程
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName() + "=" + new Random(10).nextInt());
            }).start();
        }
        //读线程
        for (int i = 0; i < 100000; i++) {
            new Thread(()->{
                String name = list.get(new Random().nextInt(list.size()));
                System.out.println(name);
            }).start();
        }
    }
}
