package cn.laoma.thread5.t5_001_HashTable2CHM;

import java.util.*;

/**
 * @program: ThreadDemos
 * @description: 测试hashMap的性能.这里其实没有太大的意义。HashMap不是线程安全的
 * 100个线程，每个线程插入10000个数据。总共100_0000条数据
 * @author: 老马
 * @create: 2021-03-07 11:25
 **/
public class T02_HashMap {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1_0000; j++) {
                    hashMap.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
                }
            });
            threads.add(thread);
        }
        threads.forEach(t -> t.start());
        long startTime = System.currentTimeMillis();
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.println("HashMap 插入"+hashMap.size()+"用时：" + (endTime-startTime) + "毫秒");


    }
}
