package cn.laoma.thread6.t6_001_Question;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @program: ThreadDemos
 * @description: 采用信号量实现。
 * 1个信号量无法实现，因为只能控制1个线程执行，但是无法控制哪个线程先执行。
 * 2个信号量注意由于第一个线程释放了T2信号量，所以T2的初始信号为0
 * 看似简单，实际很费劲的完成本例。主要是开始把第T2的初始值设置为1.无法达到预期
 * @author: 老马
 * @create: 2021-10-05 00:24
 **/
public class T09_Question_Semaphore {

    static Semaphore T1 = new Semaphore(1);
    static Semaphore T2 = new Semaphore(0);

    @SneakyThrows
    public static void main(String[] args) {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final List<String> strings = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
        new Thread(() -> {
            strings.forEach(item -> {
                try {
                    T1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(item);
                T2.release();
            });
        }, "t1").start();

        new Thread(() -> {
            integers.forEach(item -> {
                try {
                    T2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(item);
                T1.release();
            });
        }, "t2").start();
    }
}