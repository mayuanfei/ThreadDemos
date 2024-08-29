package cn.laoma.thread6.t6_004_ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: 有定时的线程池
 * @author: 老马
 * @create: 2021-10-15 10:58
 **/
public class T05_ThreadPool_Scheduled {
    public static void main(String[] args) {
        //基本不用。大概看下源码
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        service.scheduleAtFixedRate(()->{
            System.out.println(Thread.currentThread().getName());
        }, 0, 1, TimeUnit.SECONDS);
    }
}
