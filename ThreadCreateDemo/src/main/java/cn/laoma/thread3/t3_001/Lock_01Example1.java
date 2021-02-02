package cn.laoma.thread3.t3_001;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ThreadDemos
 * @description: 练习题1
 * 实现一个容器，提供两个方法：add,size方法
 * 写两个线程， 线程1添加10个元素到容器中，线程2实现监控元素的个数当个数到5个时，线程2给出提示并且结束
 * 想想能不能正常运行? 加上volatile后，能不能正常运行? volatile不要修饰对象。
 * @author: 老马
 * @create: 2021-02-01 19:29
 **/
public class Lock_01Example1 {


    /*volatile*/ List<Object> lists = new ArrayList<>();
   //List<Object> lists = Collections.synchronizedList(new ArrayList<>());

    public void  add(Object o) {
        lists.add(o);
    }

    public Integer size() {
        return lists.size();
    }

    public static void main(String[] args) {
        Lock_01Example1 example1 = new Lock_01Example1();
        //监控线程
        new Thread(() -> {
            while (true) {
                if(example1.size() == 5) {
                    break;
                }
                //try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {}
            }
            System.out.println("当前容器size=5，线程2结束");
        }, "t2").start();
        //添加线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example1.add(i);
                System.out.println("add:"+i);
                //try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {}
            }
        }, "t1").start();
    }

}
