package cn.laoma.thread4.t4_002;

import lombok.SneakyThrows;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description:虚引用
 * 1.一个对象是否有虚引用，完全不会对其生命周期构成影响
 * 2.虚引用的get方法永远获取到的数据为null
 * 作用是给开发人员检测对象是否已被GC回收
 * 应用案例对外内存回收：DirectByteBuffer
 * 典型应用。容器和ThreadLocal
 * 虚拟机参数： -verbose:gc -Xms4m -Xmx4m
 * @author: 老马
 * @Date: 2021/2/27 21:19
 */
public class Reference_04Phantom {

    @SneakyThrows
    public static void main(String[] args) {
        List<Object> list = new LinkedList<>();
        ReferenceQueue queue = new ReferenceQueue();
        LaoMa laoMa = new LaoMa();
        PhantomReference<Object> preference = new PhantomReference<>(laoMa, queue);

        // 该线程不断读取这个虚引用，并不断往列表里插入数据，以促使系统早点进行GC
        new Thread(() -> {
            while (true) {
                list.add(new byte[1024*100]);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println(preference.get());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends LaoMa> poll = queue.poll();
                if(poll != null) {
                    System.out.println("--- 虚引用对象被jvm回收了 ---- " + poll);
                }
            }
        }).start();
        laoMa = null;
        System.gc();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //测试类。垃圾回收时打印出一句话
    static class LaoMa {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("LaoMa类被GC回收了");
        }
    }

}
