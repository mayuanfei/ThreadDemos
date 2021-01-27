package cn.laoma.thread1.t1_003;

/**
 * @program: ThreadDemos
 * @description: 锁定当前类对象
 * @author: 老马
 * @create: 2021-01-23 16:04
 **/
public class Sync04 {
    private static int count = 0;

    //等价于synchronized(Sync04.class)
    public static synchronized void addCount() {
        for (int i = 0; i < 10; i++) {
            count++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("count="+count);
        }
    }


    public static /*synchronized*/ int getCount() {
        System.out.println("获得count="+count);
        return count;
    }


}
