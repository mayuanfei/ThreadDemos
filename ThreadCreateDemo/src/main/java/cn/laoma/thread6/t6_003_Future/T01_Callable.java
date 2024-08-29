package cn.laoma.thread6.t6_003_Future;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: ThreadDemos
 * @description: Callable和Runnabel差不多，都是多线程运行的最小单位。
 * 但是和Runnabel的不同在于
 * 1.Callable有返回值
 * 2.可以抛出异常
 * 3.是Future模式的基石。
 * 这个Callbale才是多线程的灵魂。笔Runable的适用面更广
 * @author: 老马
 * @create: 2021-10-06 10:21
 **/
public class T01_Callable {

    @SneakyThrows
    public static void main(String[] args) {
        //这里看下Callable的源码
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "hello world";
            }
        };

        //创建线程池运行Callable
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(callable);
        //程序会在get时阻塞
        System.out.println(future.get());
        //线程池如果不关闭会常驻内存。方法结束也不会自动释放掉。
        //另外shutdown方法在线程执行完毕后，才会去关闭
        executorService.shutdown();
    }
}
