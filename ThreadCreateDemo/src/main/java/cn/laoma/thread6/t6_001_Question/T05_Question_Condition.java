package cn.laoma.thread6.t6_001_Question;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ThreadDemos
 * @description: 用Condition实现，用两个线程等待队列来区分唤醒哪个队列；哪个队列等待。精细化的唤醒机制。
 * @author: 老马
 * @create: 2021-10-04 20:01
 **/
public class T05_Question_Condition {

    public static void main(String[] args) {

        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final List<String> strings = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                strings.forEach(item -> {
                    System.out.print(item);
                    condition2.signal();
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                condition2.signal();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                integers.forEach(item -> {
                    System.out.print(item);
                    condition1.signal();
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }, "t2").start();

    }
}