package cn.laoma.thread6.t6_003_Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @program: ThreadDemos
 * @description: CompletableFuture简介
 * 1.CompletableFuture主要是用于异步调用,实现了Future接口，内部封装了线程池【ForkJoinPool】
 * 2.CompletableFuture可以进行多线程控制调度
 * 3.可以接续。在某个行为完成之后，可以继续进行下一个动作
 * @author: 老马
 * @create: 2021-10-06 11:51
 **/
public class T03_CompletableFuture_intro {
    public static void main(String[] args){
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            System.out.println("微波炉加热饭菜");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "加热完毕";
        }).thenAccept(result -> {
            System.out.println(result + "，开始吃午饭");
        });
        System.out.println("先去厕所洗洗手");
        future.join();
    }
}
