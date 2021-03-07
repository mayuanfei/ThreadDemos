package cn.laoma.thread5.t5_002_Vector2Queue;

import java.util.ArrayList;
import java.util.List;
/**
 * @program: ThreadDemos
 * @description: 测试ArrayList多线程是否会遇到问题
 * 10个线程同时卖票。一共10000张票
 * @author: 老马
 * @create: 2021-03-07 12:25
 **/
public class T01_ArrayList_Unsafe {
    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while(tickets.size()>0) {
                    System.out.println(Thread.currentThread().getName() + "售出火车票。票号：" + tickets.remove(0));
                }
            }, "线程"+i).start();
        }
    }

}
