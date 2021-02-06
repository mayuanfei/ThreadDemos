package cn.laoma.thread4.t4_001;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: 本例演示了一个很正常的多个线程对一个对象的修改过程。
 * @author: 老马
 * @create: 2021-02-06 19:23
 **/
public class ThreadLocal_01 {

    @Data
    static class User {
        String name = "zhangsan";
    }

    public static void main(String[] args) {
        User user = new User();
        new Thread(()->{
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {}
            System.out.println(user.getName());
        }, "t1").start();

        new Thread(() -> {
            user.setName("lisi");
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}
        }, "t2").start();

    }

}
