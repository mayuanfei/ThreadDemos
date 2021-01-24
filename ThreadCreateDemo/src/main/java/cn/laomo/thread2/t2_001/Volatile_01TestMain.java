package cn.laomo.thread2.t2_001;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: voldtile 保证线程可见性；有序性；但是不保证原子性
 * @author: 老马
 * @create: 2021-01-23 11:34
 **/
public class Volatile_01TestMain {
    public volatile boolean running = true;


    @SneakyThrows
    void m1() {
        System.out.println("m1 start");
        while(running) {
            //TimeUnit.SECONDS.sleep(1);
            //System.out.println("aaa");
        }
        System.out.println("m1 end");
    }

    @SneakyThrows
    public static void main(String[] args) {
        Volatile_01TestMain test = new Volatile_01TestMain();
        new Thread(test::m1).start();
        TimeUnit.SECONDS.sleep(1);
        test.running = false;
    }
}
