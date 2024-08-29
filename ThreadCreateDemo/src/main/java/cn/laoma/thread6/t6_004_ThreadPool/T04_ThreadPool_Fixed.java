package cn.laoma.thread6.t6_004_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: ThreadDemos
 * @description: 固定大小的线程池
 * @author: 老马
 * @create: 2021-10-15 10:24
 **/
public class T04_ThreadPool_Fixed {
    public static void main(String[] args) {
        //这里看下源码和线程池大小公式.png
        //可以使程序并行处理，提高执行效率
        ExecutorService executorService = Executors.newFixedThreadPool(10);

    }
}
