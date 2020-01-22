package cn.laomo.thread;

import java.util.concurrent.Callable;

/**
 * @program: MultThreadDemos
 * @description:
 * @author: 老马
 * @create: 2020-01-16 14:59
 **/
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"执行时间："+System.currentTimeMillis()+";循环次数："+i);
        }
        return "MyCallable执行完成";
    }
}
