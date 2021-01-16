package cn.laomo.thread.t_001;


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

    //让出cpu时间片,进入可运行状态
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

}
