package cn.laomo.thread;

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
            System.out.println(Thread.currentThread().getName()+"执行时间："+System.currentTimeMillis()+";执行册数："+i);
        }
    }
}
