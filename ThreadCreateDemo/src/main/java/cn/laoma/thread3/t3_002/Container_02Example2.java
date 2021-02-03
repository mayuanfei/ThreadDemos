package cn.laoma.thread3.t3_002;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ThreadDemos
 * @description: 练习题2
 * 写一个固定容量的同步容器， 拥有put和get方法。以及getCount方法。
 * 能支持2个生成者线程以及10个消费者线程的阻塞调用
 * 用ReentrantLock实现。
 * @author: 老马
 * @create: 2021-02-03 21:11
 **/
@SuppressWarnings("all")
public class Container_02Example2<T> {
    private final int MAXSIZE = 10;
    private final LinkedList<T> list = new LinkedList<>();
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    @SneakyThrows
    public void put(T t) {
        lock.lock();
        try {
            while (list.size() == MAXSIZE) {//为啥要用wile而不是if
                producer.await();//生产者等待
            }
            list.addFirst(t);
            count++;
            consumer.signalAll();//通知消费者线程
        } finally {
            lock.unlock();
        }
    }

    @SneakyThrows
    public synchronized T get() {
        T t = null;
        lock.lock();
        try {
            while (list.size() == 0) {//为啥要用wile而不是if
                consumer.await();
            }
            t = list.removeLast();
            count--;
            producer.signalAll();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public int getCount()
    {
        lock.lock();
        try {
            return this.count;
        } finally {
            lock.unlock();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        Container_02Example2 example2 = new Container_02Example2();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5 ; j++) {
                    int o = new Random().nextInt(100);
                    example2.put(o);
                    System.out.println(String.format("放入的数值：%d；容器的size：%s",o,  example2.getCount()));
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int o = (int) example2.get();
                System.out.println(String.format("获得的数值：%d；容器的size：%s", o, example2.getCount()));
            }).start();
        }
    }
}
