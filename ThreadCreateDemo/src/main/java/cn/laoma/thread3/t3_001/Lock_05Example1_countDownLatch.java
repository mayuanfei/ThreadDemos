package cn.laoma.thread3.t3_001;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @program: ThreadDemos
 * @description: 练习题1
 * 实现一个容器，提供两个方法：add,size方法
 * 写两个线程， 线程1添加10个元素到容器中，线程2实现监控元素的个数当个数到5个时，线程2给出提示并且结束
 * 采用两个CountDownLatch实现
 * @author: 老马
 * @create: 2021-02-01 19:29
 **/
public class Lock_05Example1_countDownLatch {

    List<Object> lists = new ArrayList<>();

    public void add(Object o) {
        lists.add(o);
    }

    public Integer size() {
        return lists.size();
    }

    public static void main(String[] args) {
        Lock_05Example1_countDownLatch example1 = new Lock_05Example1_countDownLatch();
        final CountDownLatch t1CountDownLatch1 = new CountDownLatch(1);
        final CountDownLatch t2CountDownLatch2 = new CountDownLatch(1);
        //监控线程
        new Thread(() -> {
            if (example1.size() != 5) {
                try {
                    t2CountDownLatch2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("当前容器size=" + example1.size() + "，线程2结束");
            t1CountDownLatch1.countDown();
        }, "t2").start();
        //添加线程
        new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    example1.add(i);
                    System.out.println("add:" + i);
                    if (example1.size() == 5) {
                        //唤醒t2
                        t2CountDownLatch2.countDown();
                        //t1等待
                        try {
                            t1CountDownLatch1.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }, "t1").start();
    }

}
