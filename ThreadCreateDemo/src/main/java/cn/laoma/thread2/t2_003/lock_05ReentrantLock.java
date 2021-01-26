package cn.laoma.thread2.t2_003;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ThreadDemos
 * @description: 可重入锁比synchronized厉害的点
 * 可以设置公平锁和非公平锁
 * 公平锁：线程先进入等待队列FIFO执行。
 * 非公平锁：就是抢锁
 * @author: 老马
 * @create: 2021-01-26 10:53
 **/
public class lock_05ReentrantLock {
    //观察true和false的区别；默认为false
    private ReentrantLock lock = new ReentrantLock(true);

    @SneakyThrows
    public void show() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"得到了锁");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        lock_05ReentrantLock locktest = new lock_05ReentrantLock();
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(locktest::show, "t1"));
        threads.add(new Thread(locktest::show, "t2"));
        threads.forEach(t -> t.start());
    }
}
