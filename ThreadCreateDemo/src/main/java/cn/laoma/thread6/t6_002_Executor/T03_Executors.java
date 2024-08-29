package cn.laoma.thread6.t6_002_Executor;

import java.util.concurrent.Executors;

/**
 * @program: ThreadDemos
 * @description: 是一个创建线程池的工具类。可以创建常用的四种线程池。
 * @author: 老马
 * @create: 2021-10-06 10:06
 **/
public class T03_Executors {
    public static void main(String[] args) {
        //这里仅仅知道能创建四种线程池即可，不用深究每种线程池的功效。
        //创建单一线程池，容量为1的线程池。
        Executors.newSingleThreadExecutor();
        //创建指定数量的线程池
        Executors.newFixedThreadPool(2);
        //创建缓存的线程池
        Executors.newCachedThreadPool();
        //创建有定时执行的线程池
        Executors.newScheduledThreadPool(2);
    }
}
