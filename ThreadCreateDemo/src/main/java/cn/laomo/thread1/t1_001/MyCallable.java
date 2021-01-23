package cn.laomo.thread1.t1_001;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

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
            TimeUnit.SECONDS.sleep(1);
            System.out.println("MyCallable循环次数："+i);
        }
        return "MyCallable执行完成";
    }
}
