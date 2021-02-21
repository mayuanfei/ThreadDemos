package cn.laoma.thread4.t4_002;

import lombok.SneakyThrows;

/**
* @Description:强引用
* 一个强引用，如果有引用对象的话。不会被垃圾回收器回收。
* @author: 老马
* @Date: 2021/2/21 14:24
*/
public class Reference_01Normal {
    static class User{
        //仅仅用于演示代码，实际开发中不要用这个方法
        @Override
        protected void finalize() throws Throwable {
            System.out.println("user 被JVM回收了");
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        User user = new User();
        //没有引用的时候才会被回收
        user = null;
        System.gc();
        System.in.read();
    }
}
