package cn.laoma.thread2.t2_002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: ThreadDemos
 * @description: 采用cas【compare and swap】方式
 * compareAndSwapInt(Object var1, long var2, int var4, int var5)
 * var1：AtomicInteger当前对象。可以获得当前对象起始地址 如：0x00000111
 * var2：需要修改对象的偏移内存地址 如100 。0x0000011+100 = 0x0000111就是要修改的值的地址
 * var4：期望内存中的值，拿这个值和0x0000111内存中的中值比较，如果为true，则修改，返回ture,否则返回false，等待下次修改。
 * var5：如果上一步比较为ture，则把var5更新到0x0000111其实的内存中。
 * 具体说明：while中的compareAndSwapInt()方法尝试修改v的值, 该方法会通过obj和offset获取变量的值
 * 	如果这个值和v不一样, 说明其他线程修改了obj+offset地址处的值, 此时compareAndSwapInt()返回false, 继续循环
 * 	如果这个值和v一样, 说明没有其他线程修改obj+offset地址处的值, 此时可以将obj+offset地址处的值改为v+delta, compareAndSwapInt()返回true, 退出循环
 * 	Unsafe类中的compareAndSwapInt()方法是原子操作, 所以compareAndSwapInt()修改obj+offset地址处的值的时候不会被其他线程中断【cpu支持】
 * 	上面描述的示意代码：
 * 	cas(V, expectV, newV) {
 * 	    if(V == expectV) {
 * 	        V = newV
 * 	    }else {
 * 	        继续循环等待
 * 	    }
 * 	}
 * @author: 老马
 * @create: 2021-01-25 10:02
 **/
public class Cas_01AtomicInteger {

    private AtomicInteger count = new AtomicInteger(0);

    void increment(){
        for (int i = 0; i < 10000; i++) {
            count.getAndIncrement(); //相当于i++
            //count.incrementAndGet();//相当于++i
        }
    }

    public static void main(String[] args) {
        Cas_01AtomicInteger atomicInteger = new Cas_01AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(atomicInteger::increment));
        }
        threads.forEach(t -> t.start());
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        });
        System.out.println("count = " + atomicInteger.count);
    }
}
