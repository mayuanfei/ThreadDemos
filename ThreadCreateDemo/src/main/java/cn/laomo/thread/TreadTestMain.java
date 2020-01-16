package cn.laomo.thread;

import java.util.Random;

/**
 * @program: MultThreadDemos
 * @description:
 * @author: 老马
 * @create: 2020-01-16 10:27
 **/
public class TreadTestMain {
    public static void main(String[] args) throws Exception  {
        //1.测试继承Thread的方法
        testMyThread();

        //主线程执行
        for (int i=0; i<10; i++){
            Thread.sleep((int)(1+Math.random()*10));
            System.out.println("main主线程正在执行："+System.currentTimeMillis());
        }

    }

    private static void testMyThread() throws Exception {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
