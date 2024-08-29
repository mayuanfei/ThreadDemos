package cn.laoma.thread6.t6_001_Question;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @program: ThreadDemos
 * @description: 用传送队列实现
 * 原理：利用transfer要在take拿走后才能放入的原理。互相传给对方打印自己。
 * @author: 老马
 * @create: 2021-10-05 10:26
 **/
public class T11_Question_TransferQueue {

    public static void main(String[] args) {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final List<String> strings = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
        TransferQueue queue = new LinkedTransferQueue();
        new Thread(() -> {
            integers.forEach(item -> {
                try {
                    System.out.print(queue.take());
                    queue.transfer(item);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }, "t1").start();
        new Thread(() -> {
            strings.forEach(item -> {
                try {
                    queue.transfer(item);
                    System.out.print(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }, "t1").start();
    }
}