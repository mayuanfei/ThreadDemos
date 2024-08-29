package cn.laoma.thread6.t6_004_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: ThreadDemos
 * @description: 容量为1的线程池
 * 作用：可以保证线程的前后执行顺序
 * @author: 老马
 * @create: 2021-10-10 18:23
 **/
public class T02_ThreadPool_Single {
    public static void main(String[] args) {
        //这里看下源码
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            service.execute(() -> {
                System.out.println( "线程" + index + "\t" + Thread.currentThread().getName() + "执行");
            });
        }
        service.shutdown();
    }
}
