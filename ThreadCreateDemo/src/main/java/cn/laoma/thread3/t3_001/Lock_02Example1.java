package cn.laoma.thread3.t3_001;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ThreadDemos
 * @description: 练习题1
 * 实现一个容器，提供两个方法：add,size方法
 * 写两个线程， 线程1添加10个元素到容器中，线程2实现监控元素的个数当个数到5个时，线程2给出提示并且结束
 * synchronized, wait（释放锁）, notify（不释放锁），sleep（不释放锁）
 * @author: 老马
 * @create: 2021-02-01 19:29
 **/
public class Lock_02Example1 {

    List<Object> lists = new ArrayList<>();

    public void add(Object o) {
        lists.add(o);
    }

    public Integer size() {
        return lists.size();
    }

    public static void main(String[] args) {
        Lock_02Example1 example1 = new Lock_02Example1();
        final Object lock = new Object();
        //监控线程
        new Thread(() -> {
            synchronized (lock) {
                if(example1.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("当前容器size="+example1.size()+"，线程2结束");

            }
        }, "t2").start();
        //添加线程
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    example1.add(i);
                    System.out.println("add:" + i);
                    if (example1.size() == 5) {
                        lock.notify();  //不释放锁 🔒
                    }
                }
            }
        }, "t1").start();
    }

}
