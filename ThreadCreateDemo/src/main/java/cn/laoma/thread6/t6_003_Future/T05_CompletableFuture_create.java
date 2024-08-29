package cn.laoma.thread6.t6_003_Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: CompletableFuture的创建
 * @author: 老马
 * @create: 2021-10-06 16:13
 **/
public class T05_CompletableFuture_create {
    public static void main(String[] args) {
        //一般CompletableFuture使用自定义的线程池。
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        //异步任务，无返回值，采用内部的forkjoin线程池
        CompletableFuture c1 = CompletableFuture.runAsync(()->{
            System.out.println("c1,我是采用默认forkjoin线程池");

        });
        // 异步任务，无返回值，使用自定义的线程池
        CompletableFuture c11 = CompletableFuture.runAsync(()->{
            System.out.println("c11,我是采用自定义线程池");

        }, threadPool);
        // 异步任务，有返回值，使用内部默认的线程池
        CompletableFuture<String> c2 = CompletableFuture.supplyAsync(()->{
            System.out.println("c2,我是采用默认forkjoin线程池");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "我是c2";
        });
        // 异步任务，有返回值，使用自定义线程池
        CompletableFuture<String> c22 = CompletableFuture.supplyAsync(()->{
            System.out.println("c22,我是采用自定义线程池");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "我是c22";
        }, threadPool);
        // 只要有一个完成，则完成，有一个抛出异常，则携带异常
        Object join = CompletableFuture.anyOf(c2, c22).join();
        System.out.println("join--->" + join);
        CompletableFuture.anyOf(c2, c22).thenAccept(msg -> {
            System.out.println("只要有一个完成获得：" + msg);
        });
        // 必须等待所有的future全部完成才可以
        Void joinall = CompletableFuture.allOf(c2, c22).join();
        System.out.println("joinall--->" + joinall);
        CompletableFuture.allOf(c2,c22).thenAccept(msg -> {
            System.out.println("所有线程完成获得：" + msg);
        });
    }
}
