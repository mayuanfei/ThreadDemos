package cn.laomo.thread.t_002;

/**
 * @program: ThreadDemos
 * @description: 锁定当前对象,同步方法和同步方法是够可以同时运行？
 * @author: 老马
 * @create: 2021-01-20 16:04
 **/
public class Sync03Test {
    public static void main(String[] args) {
        Sync03 s3 = new Sync03();
        Thread t1 = new Thread(s3::addCount);
        Thread t2 = new Thread(s3::getCount);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
