package cn.laoma.thread1.t1_004;

/**
 * @program: ThreadDemos
 * @description: synchronized是可重入的，相当于在同一个对象加了很多把锁；父子类同样是子类的一把锁。
 * @author: 老马
 * @create: 2021-01-22 15:36
 **/
public class Sync_01Reentrant {

    private synchronized void m1() {
        System.out.println("m1 start");
        m2();
        System.out.println("m1 end");
    }

    private synchronized void m2() {
        System.out.println("m2 finished");
    }

    public static void main(String[] args) {
        new Sync_01Reentrant().m1();
    }
}
