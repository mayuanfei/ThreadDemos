package cn.laomo.thread.t_002;

/**
 * @program: ThreadDemos
 * @description: 锁定当前实例对象
 * @author: 老马
 * @create: 2021-01-20 16:04
 **/
public class Sync03 {
    private int count = 0;

    //等价于synchronized(this)
    public synchronized void addCount() {
        count++;
        System.out.println("count="+count);
    }

    public int getCount() {
        return count;
    }
    


}
