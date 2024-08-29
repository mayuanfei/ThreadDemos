package cn.laoma.thread6.t6_001_Question;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: ThreadDemos
 * @description: 采用AtomicInteger实现。原理和上面的CAS一致。其实用AtomicBoolean,AtomicLong都可以
 * @author: 老马
 * @create: 2021-10-04 20:30
 **/
public class T07_Question_AtomicInteger {


    static AtomicInteger threadNo = new AtomicInteger(1);

    public static void main(String[] args) {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final List<String> strings = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

        new Thread(() -> {
            strings.forEach(item -> {
                while (threadNo.get() != 1) {}
                System.out.print(item);
                threadNo.set(2);
            });
        }, "t1").start();

        new Thread(() -> {
            integers.forEach(item -> {
                while (threadNo.get() != 2) {}
                System.out.print(item);
                threadNo.set(1);
            });
        }, "t2").start();
    }

}