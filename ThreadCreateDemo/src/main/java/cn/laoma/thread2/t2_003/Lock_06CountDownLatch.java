package cn.laoma.thread2.t2_003;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: 向下计数的门栓
 * 能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行
 * 例子：给商户出对账表
 * @author: 老马
 * @create: 2021-01-26 10:53
 **/
public class Lock_06CountDownLatch {

    @SneakyThrows
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(3);
        new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {}
            System.out.println("取Table1表A商户昨天的所有交易信息");
            latch.countDown();
        }).start();
        new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}
            System.out.println("取Table2表A商户昨天的所有交易信息");
            latch.countDown();
        }).start();
        new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {}
            System.out.println("取Table3表A商户昨天的所有交易信息");
            latch.countDown();
        }).start();
        System.out.println("出对账表"+Thread.currentThread().getName()+"等待子线程执行完成...");
        latch.await();//阻塞当前线程，直到计数器的值为0
        System.out.println("出对账表"+Thread.currentThread().getName()+"开始执行...");

    }
}
