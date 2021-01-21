package cn.laomo.thread.t_002;

/**
 * @program: ThreadDemos
 * @description: 同步方法和同步方法是够可以同时运行？
 * @author: 老马
 * @create: 2021-01-20 20:20
 **/
public class Sync04Test {
    public static void main(String[] args) {

        Thread t1 = new Thread(Sync04::addCount);
        Thread t2 = new Thread(Sync04::getCount);
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
