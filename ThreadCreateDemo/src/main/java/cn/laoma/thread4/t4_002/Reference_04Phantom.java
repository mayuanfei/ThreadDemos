package cn.laoma.thread4.t4_002;

import lombok.SneakyThrows;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description:虚引用
 * 1.虚引用是每次垃圾回收的时候都会被回收
 * 2.虚引用的get方法永远获取到的数据为null
 * 作用是给开发人员检测对象是否已被GC回收
 * 应用案例对外内存回收：DirectByteBuffer
 * 典型应用。容器和ThreadLocal
 * @author: 老马
 * @Date: 2021/2/27 21:19
 */
public class Reference_04Phantom {

    @SneakyThrows
    public static void main(String[] args) {
        List<Object> list = new LinkedList<>();
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<Object> preference = new PhantomReference<>(new String("123456"), queue);
        System.out.println(preference.get());

        //由于这个虚引用生存时间未知，这里只能让内存溢出【触发垃圾回收，回收虚引用】。
        new Thread(() -> {
            while (true) {
                list.add(new byte[1024*1024]);
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference poll = queue.poll();
                if(poll != null) {
                    System.out.println("字符串123456被回收了");
                }
            }
        }).start();
        Thread.sleep(500);
    }

}
