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
        for (int i = 0; i < 100; i++) {
            count++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("count="+count);
    }

    public /*synchronized*/ int getCount() {
        System.out.println("获得count="+this.count);
        return count;
    }
    


}
