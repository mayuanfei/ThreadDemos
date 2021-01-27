package cn.laoma.thread2.t2_003;

import java.util.concurrent.CyclicBarrier;

/**
 * @program: ThreadDemos
 * @description: 循环栅栏
 * 例子：多人赛跑。
 * 还可以适用于：满人发车或者20人一团的情况
 * @author: 老马
 * @create: 2021-01-27 21:37
 **/
public class Lock_08CyclicBarrier {
    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(5, ()->{
            System.out.println("5位选手出发了");
        });

        for (int i = 0; i < 100; i++) {
            final int index = i;
            new Thread(() -> {
                try {Thread.sleep((long) (Math.random() * 10000));} catch (InterruptedException e) {}
                System.out.println("第" + index + "位运动员准备好了");
                try {barrier.await();} catch (Exception e) {}
            }).start();
        }
    }
}
