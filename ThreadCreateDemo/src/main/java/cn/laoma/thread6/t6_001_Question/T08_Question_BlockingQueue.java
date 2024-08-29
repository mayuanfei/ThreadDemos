package cn.laoma.thread6.t6_001_Question;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @program: ThreadDemos
 * @description: 采用阻塞队列来实现。原理为：设置容量为1，put阻塞；take运行
 * @author: 老马
 * @create: 2021-10-04 20:37
 **/
public class T08_Question_BlockingQueue {

    static BlockingQueue<String> T1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> T2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final List<String> strings = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

        new Thread(() -> {
            strings.forEach(item -> {
                System.out.print(item);
                try {
                    T1.put("ok");
                    T2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }, "t1").start();

        new Thread(() -> {
            integers.forEach(item -> {
                try {
                    T1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(item);
                try {
                    T2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }, "t2").start();
    }
}