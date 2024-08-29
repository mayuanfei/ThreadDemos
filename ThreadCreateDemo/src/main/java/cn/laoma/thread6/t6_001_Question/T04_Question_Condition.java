package cn.laoma.thread6.t6_001_Question;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ThreadDemos
 * @description: 用Condition实现,本质和wait,notify差不多
 * @author: 老马
 * @create: 2021-10-04 20:01
 **/
public class T04_Question_Condition {

    public static void main(String[] args) {

        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final List<String> strings = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                strings.forEach(item -> {
                    System.out.print(item);
                    condition.signal();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                condition.signal();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                integers.forEach(item -> {
                    System.out.print(item);
                    condition.signal();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                condition.signal();
            } finally {
                lock.unlock();
            }
        }, "t2").start();

    }
}