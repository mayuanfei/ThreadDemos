package cn.laoma.thread5.t5_001_HashTable2CHM;

import java.util.*;
/**
 * @program: ThreadDemos
 * @description: 测试同步的hashMap的性能.其实和HashTable差不多，每个方法基本都是Synchronized的。
 * 100个线程，每个线程插入10000个数据。总共100_0000条数据
 * @author: 老马
 * @create: 2021-03-07 11:25
 **/
public class T03_SynchronizedHashMap {
    public static void main(String[] args) {
        Map synchronizedMap = Collections.synchronizedMap(new HashMap());
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1_0000; j++) {
                    synchronizedMap.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
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
        System.out.println("HashMap 插入"+synchronizedMap.size()+"用时：" + (endTime-startTime) + "毫秒");

        //=================================读取测试===========================================
        threads.clear();
        //取出第100个元素的值.每个线程读取1000_0000次
        String v100 = (String) synchronizedMap.keySet().toArray()[100];
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000_0000; j++) {
                    synchronizedMap.get(v100);
                }
            });
            threads.add(thread);
        }
        threads.forEach(t -> t.start());
        startTime = System.currentTimeMillis();
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        endTime = System.currentTimeMillis();
        System.out.println("synchronizedMap 读取第100个元素用时：" + (endTime-startTime) + "毫秒");
    }
}
