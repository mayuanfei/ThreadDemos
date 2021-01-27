package cn.laoma.thread2.t2_002;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @program: ThreadDemos
 * @description: 直接操作内存对象类似c++,目前通过反射是可以用这个类的；
 * 但是JDK1.9以后就不再能使用了。
 * 不要用这个Unsafe。内存的分配销毁都需要自己处理。JVM无法帮助清理
 *
 *
 * @author: 老马
 * @create: 2021-01-26 22:01
 **/
public class Cas_02Unsafe {

    @SneakyThrows
    public static void main(String[] args) {
        //直接使用会报错
//        Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println( unsafe.addressSize());
        //通过反射获得
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        ///////////////////////////内存操作//////////////////////////////
        //分配一个8byte的内存
        long address = unsafe.allocateMemory(8L);
        //初始化内存填充1
        unsafe.setMemory(address, 8L, (byte) 1);
        //测试输出
        System.out.println("add byte to memory:" + unsafe.getInt(address));
        //设置0-3 4个byte为0x7fffffff
        unsafe.putInt(address, 0x7fffffff);
        //设置4-7 4个byte为0x80000000
        unsafe.putInt(address + 4, 0x80000000);
        //int占用4byte
        System.out.println("add byte to memory:" + unsafe.getInt(address));

    }
}
