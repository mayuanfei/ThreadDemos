package cn.laoma.thread2.t2_003;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ThreadDemos
 * @description: 可重入锁比synchronized厉害的点
 * 对于那些未竞争的到锁，而 可以被外部调用interrupt()来中断，
 * 从而达到不在等候锁资源，不再去竞争锁
 * @author: 老马
 * @create: 2021-01-26 10:53
 **/
public class lock_04ReentrantLock {

    @SneakyThrows
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                for (int i = 0; i < 10; i++) {
                    System.out.println("t2等待锁==>" + i + "秒");
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                System.out.println("t2 被打断，不再等待锁了。");
            } finally {
                try {
                    lock.unlock();
                    System.out.println("t2 锁被释放");
                }catch (Exception e) {
                    System.out.println("没有得到锁的线程运行结束");
                }
            }
        });
        t2.start();
        TimeUnit.SECONDS.sleep(3);
        t2.interrupt();
    }
}
