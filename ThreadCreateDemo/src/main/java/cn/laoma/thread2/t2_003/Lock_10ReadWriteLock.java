package cn.laoma.thread2.t2_003;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: ThreadDemos
 * @description: 读写锁
 * 读可以共享；写需要排他
 * 观察用lock和readLock、writeLock的区别
 * @author: 老马
 * @create: 2021-01-28 09:48
 **/
public class Lock_10ReadWriteLock {
    static volatile int  count = 0;
    static Lock lock = new ReentrantLock();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    //读数据
    public static void read(Lock lock) {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("读完毕：" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //写数据
    public static void wirte(Lock lock, int countVal) {
        lock.lock();
        try {
            Thread.sleep(1000);
            count = countVal;
            System.out.println("写完毕：" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            if(i==5 || i==10 || i==15) {
                new Thread(() -> Lock_10ReadWriteLock.wirte(lock, new Random().nextInt(100))).start();
            }else {
                new Thread(() -> Lock_10ReadWriteLock.read(lock)).start();
            }
        }
    }

}
