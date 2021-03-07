package cn.laoma.thread5.t5_002_Vector2Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: 测试Vector多线程是否会遇到问题
 * 10个线程同时卖票。一共10000张票
 * @author: 老马
 * @create: 2021-03-07 12:25
 **/
public class T04_Queue {
    static ConcurrentLinkedQueue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票号：" + i);
        }
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    String ticket = tickets.poll();
                    if (ticket == null) break;
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "售出火车票。票号：" + ticket);
                }
            }, "线程" + i);
            threads.add(thread);
        }

        threads.forEach(t -> t.start());
        long startTime = System.currentTimeMillis();
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.println("Queue售票用时：" + (endTime-startTime) + "毫秒");
    }

}
