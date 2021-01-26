package cn.laoma.thread2.t2_003;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ThreadDemos
 * @description: 可重入锁比synchronized厉害的点
 * 与synchronized关键字不同，获取到锁的线程能够响应中断，
 * 当获取到锁的线程被中断时，中断异常将会被抛出，同时锁会被释放
 * @author: 老马
 * @create: 2021-01-26 10:53
 **/
public class Lock_03ReentrantLock {
    @SneakyThrows
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                for (int i = 1; i < 10; i++) {
                    System.out.println("i = " + i);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                System.out.println("t1被打断");
            } finally {
                lock.unlock();
                System.out.println("锁被释放");
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        t1.interrupt();
    }
}
