package cn.laomo.thread.t_002;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ThreadDemos
 * @description: 锁定当前对象
 * @author: 老马
 * @create: 2021-01-20 16:04
 **/
public class Sync02 {
    private int count = 0;

    //synchronized (this)只能锁定同一个实例对象。不同的实例对象锁不住的。
    //比如以前spring结合Struts时，没有请求都是一个新的对象实例。如果在controller中用synchronized是达不到效果的
    public void addCount() {
        synchronized (this) {
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
