package cn.laoma.thread3.t3_003;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ThreadDemos
 * @description: 本例子仅仅用于阅读源码用。
 * @author: 老马
 * @create: 2021-02-05 17:23
 **/
public class AQS_01 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
