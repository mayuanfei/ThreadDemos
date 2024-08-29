package cn.laoma.thread6.t6_003_Future;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @program: ThreadDemos
 * @description: 这就是我们经常说的Futrue模式。可以大大提高效率。
 * 主要用于有返回值的多线程任务。
 * @author: 老马
 * @create: 2021-10-06 10:45
 **/
public class T02_Future {
    @SneakyThrows
    public static void main(String[] args) {
        /**************************************************************
         * 采用FutureTask执行。FutureTask既是一个Futrue也是一个Task，Callable
         * 只能作为一个Task而无法拿到结果.而FutureTask不但能执行线程同时还能
         * 存储结果。底层通过实现RunnableFuture从而具有这两种能力。
         *************************************************************/
        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            TimeUnit.SECONDS.sleep(1);
            return 100;
        });
        new Thread(futureTask).start();
        System.out.println("是否执行完成：" + futureTask.isDone());
        Integer integer = futureTask.get();
        System.out.println("是否执行完成：" + futureTask.isDone());
        System.out.println(integer);

        /**************************************************************
         * 采用多线程方式执行
         *************************************************************/
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            return 100;
        });
        System.out.println("是否执行完成：" + future.isDone());
        Integer integer1 = future.get();
        System.out.println("是否执行完成：" + future.isDone());
        System.out.println(integer1);
        executorService.shutdown();
    }
}
