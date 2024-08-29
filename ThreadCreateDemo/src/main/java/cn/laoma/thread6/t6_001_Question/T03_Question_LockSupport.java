package cn.laoma.thread6.t6_001_Question;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: ThreadDemos
 * @description: 用LockSupport实现
 * @author: 老马
 * @create: 2021-10-04 19:54
 **/
public class T03_Question_LockSupport {

    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {

        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final List<String> strings = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

        t1 = new Thread(() -> strings.forEach(item -> {
            System.out.print(item);
            LockSupport.unpark(t2);
            LockSupport.park();
        }), "t1");

        t2 = new Thread(() -> integers.forEach(item -> {
            LockSupport.park();
            System.out.print(item);
            LockSupport.unpark(t1);
        }), "t2");

        t1.start();
        t2.start();
    }
}