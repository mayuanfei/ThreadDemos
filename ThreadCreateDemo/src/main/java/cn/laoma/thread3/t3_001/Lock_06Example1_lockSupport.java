package cn.laoma.thread3.t3_001;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: ThreadDemos
 * @description: 练习题1
 * 实现一个容器，提供两个方法：add,size方法
 * 写两个线程， 线程1添加10个元素到容器中，线程2实现监控元素的个数当个数到5个时，线程2给出提示并且结束
 * 采用lockSupport实现
 * @author: 老马
 * @create: 2021-02-01 19:29
 **/
public class Lock_06Example1_lockSupport {

    List<Object> lists = new ArrayList<>();

    public void add(Object o) {
        lists.add(o);
    }

    public Integer size() {
        return lists.size();
    }

    static Thread t1 , t2 ;
    public static void main(String[] args) {
        Lock_06Example1_lockSupport example1 = new Lock_06Example1_lockSupport();

        //监控线程
        t2 = new Thread(() -> {
            if (example1.size() != 5) {
                LockSupport.park();
            }
            System.out.println("当前容器size=" + example1.size() + "，线程2结束");
            LockSupport.unpark(t1);
        }, "t2");
        //添加线程
        t1 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    example1.add(i);
                    System.out.println("add:" + i);
                    if (example1.size() == 5) {
                        //唤醒t2
                        LockSupport.unpark(t2);
                        //t1等待
                        LockSupport.park();
                    }
            }
        }, "t1");
        t2.start();
        t1.start();
    }

}
