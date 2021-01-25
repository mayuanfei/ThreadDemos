package cn.laoma.thread2.t2_002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: ThreadDemos
 * @description: 采用cas【compare and swap】方式
 * while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4))
 * var1(obj) : Unsafe对象本身，需要通过这个类来获取偏移地址中的值
 * var2(offset): 内存偏移地址
 * var5(v) : 期望更新的值。
 * var5 + var4 (v + delta): 要更新的值
 * 具体说明：while中的compareAndSwapInt()方法尝试修改v的值,具体地, 该方法也会通过obj和offset获取变量的值
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
