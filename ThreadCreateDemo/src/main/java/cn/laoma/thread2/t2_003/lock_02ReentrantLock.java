package cn.laoma.thread2.t2_003;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ThreadDemos
 * @description: 可重入锁比synchronized厉害的点
 * 实验trylock
 * @author: 老马
 * @create: 2021-01-26 09:49
 **/
public class lock_02ReentrantLock {
    Lock lock = new ReentrantLock();
    public void m1() {
        lock.lock();
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                    TimeUnit.SECONDS.sleep(1);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        try {
            boolean b = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2是否获得锁："+b);
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        lock_02ReentrantLock lockTest = new lock_02ReentrantLock();
        new Thread(lockTest::m1).start();
        TimeUnit.MILLISECONDS.sleep(1);
        new Thread(lockTest::m2).start();
    }


}
