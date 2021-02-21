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
        WeakReference<Object> m = new WeakReference<>(new Object());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());
    }
}
