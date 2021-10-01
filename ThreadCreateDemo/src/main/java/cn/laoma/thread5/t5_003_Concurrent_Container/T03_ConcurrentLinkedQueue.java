package cn.laoma.thread5.t5_003_Concurrent_Container;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @program: ThreadDemos
 * @description: 队列
 * @author: 老马
 * @create: 2021-09-28 16:47
 **/
public class T03_ConcurrentLinkedQueue {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedDeque<>();
        //add和offer都是添加元素，不同点在于
        //在容量已满的情况下，add() 方法会抛出IllegalStateException异常，offer() 方法只会返回 false
        boolean add = queue.add("11");
        boolean offer = queue.offer("22");
    }
}
