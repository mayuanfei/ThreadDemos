package cn.laoma.thread1.t1_004;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: 异常是会自动释放锁的。其他线程可以拿到有问题的数据，实际编程中小心
 * @author: 老马
 * @create: 2021-01-22 15:50
 **/
public class Sync_02Exception {
    //没有出款的状态
    int state = 0;

    //对钱进行处理，要小心
    @SneakyThrows
    private synchronized void m1() {
        String name = Thread.currentThread().getName();
        System.out.println(Thread.currentThread().getName() + "进来进行出款操作了。");
        if (state == 0) {
            for (int i = 0; i < 10; i++) {
                System.out.println("出款人"+i+"进行了出款");
                TimeUnit.SECONDS.sleep(1);
                if(name.equals("t1") && i == 5) {
                    //异常产生，可以采用事务回滚也可以捕获异常继续，视具体业务场景而定
                    int j = i/0;
                    System.out.println(j);
                }
            }
            //标识处理完成
            state =1;
        }
        System.out.println(Thread.currentThread().getName() + "出款操作成功完成了。");
    }

    @SneakyThrows
    public static void main(String[] args) {
        Sync_02Exception s2 = new Sync_02Exception();
        new Thread(s2::m1, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(s2::m1, "t2").start();

    }
}
