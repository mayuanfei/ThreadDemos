package cn.laomo.thread.t_002;

/**
 * @program: ThreadDemos
 * @description: 锁定当前类对象
 * @author: 老马
 * @create: 2021-01-20 16:04
 **/
public class Sync04 {
    private static int count = 0;

    //等价于synchronized(Sync04.class)
    public static synchronized void addCount() {
        count++;
        System.out.println("count="+count);
    }


    public static int getCount() {
        return count;
    }


}
