package cn.laomo.thread2.t2_001;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: ThreadDemos
 * @description: volatile不能保证原子性的例子
 * @author: 老马
 * @create: 2021-01-24 20:56
 **/
public class Volatile_04 {
    volatile int count = 0;

    /*synchronized*/ void  increment(){
        for (int i = 0; i < 1000; i++) {
            count ++;
        }
    }

    public static void main(String[] args) {
        Volatile_04 test4 = new Volatile_04();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(test4::increment));
        }
        threads.forEach(t -> t.start());
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        });
        System.out.println("count = " + test4.count);
    }
}
