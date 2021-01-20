package cn.laomo.thread.t_002;

/**
 * @program: ThreadDemos
 * @description:
 * @author: 老马
 * @create: 2021-01-20 20:20
 **/
public class Sync04Test {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 100; j++) {
                    Sync04.addCount();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = Sync04.getCount();
                System.out.println("获得count--->" + count);
            }
        });
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
