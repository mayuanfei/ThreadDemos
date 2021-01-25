package cn.laoma.thread1.t1_001;

import java.util.concurrent.TimeUnit;

/**
 * @program: MultThreadDemos
 * @description: 通过实现Runnable接口
 * @author: 老马
 * @create: 2020-01-16 14:50
 **/
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("MyRunnabel执行册数："+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
