package cn.laoma.thread2.t2_003;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: ThreadDemos
 * @description: 锁支持
 * 作用：阻塞线程用的
 * @author: 老马
 * @create: 2021-01-28 14:51
 **/
public class Lock_14LockSupport {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("index = " + i);
                if(i == 3) {
                    LockSupport.park();
                }
                try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}
            }
        });
        t1.start();
        //unpark在park之前执行
//        LockSupport.unpark(t1);
        //unpark在park之后执行
        try {TimeUnit.SECONDS.sleep(7);} catch (InterruptedException e) {}
        System.out.println("释放t1的锁");
        LockSupport.unpark(t1);
    }
}
