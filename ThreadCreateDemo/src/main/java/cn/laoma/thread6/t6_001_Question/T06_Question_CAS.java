package cn.laoma.thread6.t6_001_Question;

import java.util.Arrays;
import java.util.List;

/**
 * @program: ThreadDemos
 * @description: 采用自旋的方式实现。这个方式会和wait，park不同，会占用CPU资源
 * @author: 老马
 * @create: 2021-10-04 20:16
 **/
public class T06_Question_CAS {

    enum CurrentRunThread {T1, T2}
    //注意这里为什么要用volatile
    static volatile CurrentRunThread runThread = CurrentRunThread.T1;

    public static void main(String[] args) {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final List<String> strings = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

        new Thread(() -> {
            strings.forEach(item -> {
                while (runThread != CurrentRunThread.T1) {}
                System.out.print(item);
                runThread = CurrentRunThread.T2;
            });
        }, "t1").start();

        new Thread(() -> {
            integers.forEach(item -> {
                while (runThread != CurrentRunThread.T2) {}
                System.out.print(item);
                runThread = CurrentRunThread.T1;
            });
        }, "t2").start();
    }

}