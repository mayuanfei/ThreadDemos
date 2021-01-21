package cn.laomo.thread.t_002;
import java.util.Random;

/**
 * @program: ThreadDemos
 * @description: static锁和非static锁互相不影响
 * 看打印出来的时间。可以看到线程有同时执行的情况。
 * @author: 老马
 * @create: 2021-01-21 15:51
 **/
public class Sync05 {
    //静态方法加锁
    public synchronized static void testStaticSynchronized() {
        System.out.println("静态方法加锁 --> " + System.currentTimeMillis());
    }
    //非静态方法加锁
    public synchronized void testNonStaticSynchronized() {
        System.out.println("非静态方法加锁 --> " + System.currentTimeMillis());
    }
    public static void main(String[] args) {
        //创建5个线程调用非静态方法
        for (int i = 0; i < 5; i++) {
            Sync05 sync05 = new Sync05();
            new Thread(() -> {
                try {
                    Thread.sleep(new Random().nextInt(5) * 1000);
                } catch (InterruptedException e) {
                }
                sync05.testNonStaticSynchronized();
            }, "NonStaticThread"+i).start();
        }
        //创建5个线程调用静态方法
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(new Random().nextInt(5) * 1000);
                } catch (InterruptedException e) {
                }
                Sync05.testStaticSynchronized();
            }, "StaticThread"+i).start();
        }
    }
}



