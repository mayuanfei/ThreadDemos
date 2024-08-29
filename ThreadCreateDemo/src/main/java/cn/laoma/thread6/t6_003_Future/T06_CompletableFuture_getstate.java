package cn.laoma.thread6.t6_003_Future;

import java.util.concurrent.*;

/**
 * @program: ThreadDemos
 * @description: CompletableFuture的取值和状态
 * @author: 老马
 * @create: 2021-10-07 10:10
 **/
public class T06_CompletableFuture_getstate {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // 异步任务，有返回值，使用自定义线程池
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            System.out.println("我是采用自定义线程池");
            try {
                TimeUnit.SECONDS.sleep(5);
                throw new RuntimeException("模拟抛出异常");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "我是future";
        }, threadPool);
        // 不抛出异常，阻塞的等待
//        String result = future.join();
//        System.out.println("获得结果：" + result);

        // 有异常则抛出异常，阻塞的等待，无限等待
//        String result = null;
//        try {
//            result = future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            //在执行异常中可以捕获运行中出现的异常
//            e.printStackTrace();
//        }
//        System.out.println("获得结果：" + result);

        // 有异常则抛出异常，最长等待多久，如果到时间还没有得到数据，则异常。
        //这里测试下timeout为4秒和6秒的区别
        try {
            future.get(6,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //这里是运行时异常
            e.printStackTrace();
        } catch (TimeoutException e) {
            //这里是超时异常
            e.printStackTrace();
        }

        threadPool.shutdown();

    }
}
