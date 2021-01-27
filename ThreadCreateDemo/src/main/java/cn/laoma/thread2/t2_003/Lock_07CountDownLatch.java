package cn.laoma.thread2.t2_003;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: 向下计数的门栓
 * 可以使多个线程并行开始执行
 * 例子：多人赛跑。
 * @author: 老马
 * @create: 2021-01-26 20:53
 **/
public class Lock_07CountDownLatch {

    @SneakyThrows
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {}
            System.out.println("运动员A准备就绪");
            try {latch.await();} catch (InterruptedException e) {}
            System.out.println("运动员A出发："+System.currentTimeMillis());
            try {Thread.sleep((long) (Math.random() * 10000));} catch (InterruptedException e) {}
            System.out.println("运动员A到达终端");
        }).start();
        new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}
            System.out.println("运动员B准备就绪");
            try {latch.await();} catch (InterruptedException e) {}
            System.out.println("运动员B出发："+System.currentTimeMillis());
            try {Thread.sleep((long) (Math.random() * 10000));} catch (InterruptedException e) {}
            System.out.println("运动员B到达终端");
        }).start();
        new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {}
            System.out.println("运动员C准备就绪");
            try {latch.await();} catch (InterruptedException e) {}
            System.out.println("运动员C出发："+System.currentTimeMillis());
            try {Thread.sleep((long) (Math.random() * 10000));} catch (InterruptedException e) {}
            System.out.println("运动员C到达终端");
        }).start();
        System.out.println("裁判准备就位");
        try {TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) {}
        System.out.println("发令枪打响了..................");
        System.out.println("=============================");
        latch.countDown();

    }
}
