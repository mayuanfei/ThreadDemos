package cn.laoma.thread2.t2_003;

import java.util.concurrent.Exchanger;

/**
 * @program: ThreadDemos
 * @description: 交换器
 * 作用：两个线程交换数据。用于遗传算法
 * 当两个线程都达到同步点的时候，才可以交换信息，否则先到达同步点的线程必须等待。
 * 了解一下即可
 * @author: 老马
 * @create: 2021-01-28 14:51
 **/
public class Lock_13Exchanger {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            String s1 = "嘻嘻嘻";
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"到达同步点");
                String s2 = exchanger.exchange(s1);
                System.out.println(Thread.currentThread().getName()+"交互的数据："+s2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            String s2 = "哈哈哈";
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"到达同步点");
                String s1 = exchanger.exchange(s2);
                System.out.println(Thread.currentThread().getName()+"交互的数据："+s1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();


    }
}
