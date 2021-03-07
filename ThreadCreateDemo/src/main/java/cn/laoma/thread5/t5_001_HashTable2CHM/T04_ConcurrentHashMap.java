package cn.laoma.thread5.t5_001_HashTable2CHM;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: ThreadDemos
 * @description: 测试ConcurrentHashMap的性能.插入和HashTable比可能慢些，但是读取快
 * 100个线程，每个线程插入10000个数据。总共100_0000条数据
 * @author: 老马
 * @create: 2021-03-07 11:25
 **/
public class T04_ConcurrentHashMap {

    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1_0000; j++) {
                    concurrentHashMap.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
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
        System.out.println("ConcurrentHashMap 插入"+concurrentHashMap.size()+"用时：" + (endTime-startTime) + "毫秒");

        //=================================读取测试===========================================
        threads.clear();
        //取出第100个元素的值.每个线程读取1000_0000次
        String v100 = (String) concurrentHashMap.keySet().toArray()[100];
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000_0000; j++) {
                    concurrentHashMap.get(v100);
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
        System.out.println("concurrentHashMap 读取第100个元素用时：" + (endTime-startTime) + "毫秒");
    }
}
