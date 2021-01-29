package cn.laoma.thread2.t2_003;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: 信号量
 * 限流。控制同时并行的线程数量
 * 例子：3个售票窗口
 * @author: 老马
 * @create: 2021-01-28 09:48
 **/
public class Lock_12SemaphoreLock {
    static Semaphore sp = new Semaphore(3);

    static class Persion extends Thread {
        private String name;
        public Persion(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            try {
                sp.acquire();
                System.out.println(name + "到达窗口买票");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(name + "买完票，释放了这个窗口");
                sp.release();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Persion("P" + i).start();
        }
    }

}
