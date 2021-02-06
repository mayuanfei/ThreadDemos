package cn.laoma.thread4.t4_001;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: 用ThreadLocal演示每个线程有自己的副本
 * 这里模拟线程2在1秒后设置的user，在线程1中2秒后也是拿不到的
 * ThreadLocal通过ThreadLocalMap来维护一个线程和值的对应关系
 *
 * @author: 老马
 * @create: 2021-02-06 19:23
 **/
public class ThreadLocal_02 {
    static ThreadLocal<User> local = new ThreadLocal<>();

    static {
        local.set(new User());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        String name = "zhangsan";
    }

    public static void main(String[] args) {
        new Thread(()->{
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {}
            System.out.println("t1===>" + local.get());
            local.remove();//避免内存泄漏最好用过后移除掉
        }, "t1").start();

        new Thread(() -> {
            local.set(new User("lisi"));
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}
            System.out.println("t2===>" + local.get().getName());
            local.remove();
        }, "t2").start();
        //main线程的user值
        System.out.println("main===>" + local.get().getName());

    }

}
