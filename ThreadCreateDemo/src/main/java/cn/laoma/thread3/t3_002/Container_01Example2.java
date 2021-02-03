package cn.laoma.thread3.t3_002;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Random;

/**
 * @program: ThreadDemos
 * @description: 练习题2
 * 写一个固定容量的同步容器， 拥有put和get方法。以及getCount方法。
 * 能支持2个生成者线程以及10个消费者线程的阻塞调用
 * 用wait notify实现。
 * 没有实现生产者线程只叫醒消费者线程；消费者线程只叫醒生产者线程。
 * @author: 老马
 * @create: 2021-02-03 21:11
 **/
public class Container_01Example2<T> {
    private final int MAXSIZE = 10;
    private final LinkedList<T> list = new LinkedList<>();
    private int count = 0;

    @SneakyThrows
    public synchronized void put(T t) {
        while (list.size() == MAXSIZE) {//为啥要用wile而不是if
            this.wait();
        }
        list.addFirst(t);
        count++;
        this.notifyAll();
    }

    @SneakyThrows
    public synchronized T get() {
        while (list.size() == 0) {//为啥要用wile而不是if
            this.wait();
        }
        T t = list.removeLast();
        count--;
        this.notifyAll();
        return t;
    }

    public synchronized int getCount() {
        return this.count;
    }

    @SneakyThrows
    public static void main(String[] args) {
        Container_01Example2 example2 = new Container_01Example2();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5 ; j++) {
                    int o = new Random().nextInt(100);
                    example2.put(o);
                    System.out.println("放入的数值：" + o);
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int o = (int) example2.get();
                System.out.println("获得的数值：" + o);
            }).start();
        }
    }
}
