package cn.laoma.thread2.t2_003;

import java.util.concurrent.locks.StampedLock;

/**
 * @program: ThreadDemos
 * @description: 邮戳锁
 * ReadWriteLock读的过程中不允许写，是一种悲观的读锁
 * JDK8引入的新锁。 读的过程中也允许获取写锁后然后写入，采用乐观的读锁
 * @author: 老马
 * @create: 2021-01-28 09:48
 **/
public class Lock_11StampedLock {
    final static StampedLock stampedLock = new StampedLock();

    static double x;
    static double y;

    //修改x,y的值
    static void move(double _x, double _y) {
        //获得写锁，返回一个邮戳号【或者理解成版本号】
        long stamp = stampedLock.writeLock();
        try {
            x += _x;
            y += _y;
        } finally {
            //通过这个邮戳号释放写锁
            stampedLock.unlockWrite(stamp);
        }
    }

    //计算x,y 到原点的距离
    static double distanceFromOrigin() {
        //获得乐观读锁
        long stamp = stampedLock.tryOptimisticRead();
        // 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
        double curX = x;
        // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        double curY = y;
        // 此处已读取到y，如果没有写入，读取是正确的(100,200)
        // 如果有写入，读取是错误的(100,400)
        if (!stampedLock.validate(stamp)) {
            //获得悲观读锁
            long readStamp = stampedLock.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                //通过这个邮戳号释放悲观读锁
                stampedLock.unlockRead(readStamp);
            }
        }
        //计算到原点的距离
        return Math.sqrt(curX * curX + curY * curY);
    }


}
