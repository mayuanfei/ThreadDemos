package cn.laoma.thread4.t4_002;

import java.lang.ref.WeakReference;

/**
* @Description:弱引用
 * 存活到下一个gc
 * 典型应用。容器和ThreadLocal
* @author: 老马
* @Date: 2021/2/21 15:30
*/
public class Reference_03Weak {
    public static void main(String[] args) {
        //存活到下一个GC
        WeakReference<Object> m = new WeakReference<>(new Object());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        //弱引用引用的对象如果没有被垃圾回收器回收，那么弱引用也不会被回收
        Object o = new Object();
        WeakReference<Object> n = new WeakReference<>(o);
        System.out.println(n.get());
        //o = null;
        System.gc();
        System.out.println(n.get());
    }
}
