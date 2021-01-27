package cn.laoma.thread1.t1_002;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description:
 * @author: 老马
 * @create: 2021-01-20 22:20
 **/
public class ThreadState {

    @SneakyThrows
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                TimeUnit.SECONDS.sleep(1);
            }
        });
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
        t1.join();
        System.out.println(t1.getState());
    }
}
