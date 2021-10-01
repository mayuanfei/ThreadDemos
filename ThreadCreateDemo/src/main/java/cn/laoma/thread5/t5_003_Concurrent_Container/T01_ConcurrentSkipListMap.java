package cn.laoma.thread5.t5_003_Concurrent_Container;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @program: ThreadDemos
 * @description: 以前排序的map是用treeMap，高并发下用ConcurrentSkipListMap进行排序
 * @author: 老马
 * @create: 2021-03-09 09:20
 **/
public class T01_ConcurrentSkipListMap {

    public static void main(String[] args) {
        //Map<Integer, Integer > map = new ConcurrentHashMap<>();//没有排序功能
        Map<Integer, Integer > map = new ConcurrentSkipListMap<>();//高并发并且排序。通过跳表实现。

        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put(new Random().nextInt(10000), new Random().nextInt(10000));
                    latch.countDown();
                }
            });
        }
        Arrays.asList(threads).forEach(t -> t.start());
        try {
            latch.countDown();
        }catch (Exception e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(JSON.toJSON(map));
    }
}
