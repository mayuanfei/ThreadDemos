package cn.laoma.thread1.t1_003;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ThreadDemos
 * @description: 锁定对象
 * @author: 老马
 * @create: 2021-01-20 16:04
 **/
public class Sync01 {
    private int count = 0;
    private Object obj = new Object();

    public void addCount() {
        synchronized (obj) {
            count++;
        }
    }

    @Test
    public void testAddCount() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    addCount();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }
        //等所有线程执行结束，打印出count；
        threads.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("count====>" + count);

    }


}
