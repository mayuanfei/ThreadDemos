package cn.laoma.thread2.t2_003;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ThreadDemos
 * @description: 可重入锁。
 * 这里相当于synchronized（this）
 * @author: 老马
 * @create: 2021-01-26 20:49
 **/
public class Lock_01ReentrantLock {
    int count = 0;
    Lock lock = new ReentrantLock();

    public void addCount() {
        lock.tryLock();
        try {
            count += 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return count;
    }

    @SneakyThrows
    public static void main(String[] args) {
        Lock_01ReentrantLock lockTest = new Lock_01ReentrantLock();
        for (int i = 0; i < 100; i++) {
            new Thread(lockTest::addCount).start();
        }
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println(lockTest.getCount());

    }


}
