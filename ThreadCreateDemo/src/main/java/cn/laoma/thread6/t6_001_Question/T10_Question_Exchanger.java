package cn.laoma.thread6.t6_001_Question;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @program: ThreadDemos
 * @description: 用交换器实现.
 * 实现原理：在exchange时线程会cas等待。
 * @author: 老马
 * @create: 2021-10-05 02:26
 **/
public class T10_Question_Exchanger {

    static Exchanger<String> exchanger = new Exchanger();

    @SneakyThrows
    public static void main(String[] args) {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final List<String> strings = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
        Thread t1 = new Thread(() -> {
            strings.forEach(item -> {
                try {
                    System.out.print(item);
                    String t22 = exchanger.exchange(item);
                    System.out.print(t22);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }, "t1");

        Thread t2 = new Thread(() -> {
            integers.forEach(item -> {
                try {
                    exchanger.exchange(""+item);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }, "t2");

        t1.start();t2.start();
    }
}