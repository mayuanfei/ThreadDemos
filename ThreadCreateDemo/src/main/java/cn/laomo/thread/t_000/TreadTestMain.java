package cn.laomo.thread.t_000;

import java.util.concurrent.*;

/**
 * @program: MultThreadDemos
 * @description: 线程实现的3种方式
 * @author: 老马
 * @create: 2020-01-16 10:27
 **/
public class TreadTestMain {
    public static void main(String[] args) throws Exception  {
        //1.测试继承Thread的方式
        new MyThread().run();
        new MyThread().start();

        //2.测试实现Runnable接口的方式
        new Thread(new MyRunnable()).start();

        //3.测试实现Callable接口的方式
        FutureTask<String> futureTask = new FutureTask(new MyCallable());
        new Thread(futureTask).start();

        //主线程执行
        for (int i=0; i<10; i++){
            TimeUnit.SECONDS.sleep(1);
            System.out.println("main主线程正在执行："+i);
        }

        //get方法会阻塞线程，所以要在不需要并行的地方获得其值
        String result = futureTask.get();
        System.out.println("MyCallabel执行结果：" + result);

        //采用线程池方式执行
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(new MyCallable());
        Future<?> submit1 = executorService.submit(new MyRunnable());
        submit.get();
        submit1.get();

    }
}
