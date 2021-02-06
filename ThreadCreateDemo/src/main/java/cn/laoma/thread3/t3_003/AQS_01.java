package cn.laoma.thread3.t3_003;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ThreadDemos
 * @description: 本例子仅仅用于阅读源码用。
 * AQS核心一个state，一个链表
 * 所有的操作都是基于cas操作，效率高。
 * @author: 老马
 * @create: 2021-02-05 17:23
 **/
public class AQS_01 {
    private static volatile  int count = 0;
    ReentrantLock lock = new ReentrantLock();

    public void addCount() {
        lock.lock();
        try {
            count ++;
            System.out.println("count="+count);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        AQS_01 aqs = new AQS_01();
        for (int i = 0; i < 10; i++) {
            new Thread(aqs::addCount, "t"+i).start();
        }
    }
}
