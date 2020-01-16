package cn.laomo.thread;

import java.util.concurrent.FutureTask;

/**
 * @program: MultThreadDemos
 * @description:
 * @author: 老马
 * @create: 2020-01-16 10:27
 **/
public class TreadTestMain {
    public static void main(String[] args) throws Exception  {
        //1.测试继承Thread的方式
//        testMyThread();

        //2.测试实现Runnable接口的方式
//        testMyRunnable();

        //3.测试实现Callable接口的方式
        FutureTask<String> futureTask = testMyCallabel();

        //主线程执行
        for (int i=0; i<10; i++){
            System.out.println("main主线程正在执行："+System.currentTimeMillis());
        }

        String result = futureTask.get();
        System.out.println("MyCallabel执行结果：" + result);

    }
    //1.测试继承Thread的方式
    private static void testMyThread() throws Exception {
        MyThread myThread = new MyThread();
        myThread.start();
    }
    //2.测试实现Runnable接口的方式
    private static void testMyRunnable() throws Exception {
        Thread thread = new Thread(new MyRunnable(), "MyRunnabel");
        thread.start();
    }
    //3.测试实现Callable接口的方式
    private static FutureTask<String> testMyCallabel() throws Exception {
        //创建FutureTask实例，参数传入MyCallable实例
        FutureTask<String> futureTask = new FutureTask(new MyCallable());
        //创建Thread实例，执行FutureTask
        Thread thread = new Thread(futureTask);
        thread.start();
        return futureTask;
    }
}
