package cn.laomo.thread1.t1_002;


import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SleepYieldJoin {


    //线程睡眠2秒。线程sleep后进入定时等待状态，sleep完成变成可运行状态
    @Test
    public void testSleep() throws Exception{
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("线程睡眠2秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //为了可以打印出上面的“线程睡眠2秒”。
        Thread.sleep(2000);
    }

    //让出cpu时间片,进入Ready状态
    @Test
    public void testYield() throws Exception {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("第1个线程："+i);
                if(i == 10) {
                    Thread.yield();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("第2个线程："+i);

            }
        }).start();
        TimeUnit.SECONDS.sleep(10);
    }

    //让加入的线程先执行完。然后在执行本线程。本线程会进入等待状态
    @Test
    public void testJoin() throws Exception {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("第1个线程：" + i);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("第2个线程：" + i);
            }
        });
        t1.start();
        t2.start();
        t2.join();
    }

}
