package cn.laoma.thread6.t6_004_ThreadPool;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: 缓存线程池
 * @author: 老马
 * @create: 2021-10-15 09:57
 **/
public class T03_ThreadPool_Cached {

    @SneakyThrows
    public static void main(String[] args) {
        //有线程没有回收，那么就用已经存在的线程；如果没有空闲的线程那么创建一个新线程。
        //队列容量为0，这里看下源码
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);
        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(service);
        TimeUnit.SECONDS.sleep(3);
        System.out.println(service);
    }

}
