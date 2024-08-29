package cn.laoma.thread6.t6_003_Future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @program: ThreadDemos
 * @description: CompletableFuture的接续方式
 * CompletableFuture + (Runnable,Consumer,Function)
 * CompletableFuture + CompletableFuture
 * CompletableFuture + 处理结果
 * @author: 老马
 * @create: 2021-10-07 18:03
 **/
public class T07_CompletableFuture_callback {
    //第一种接续方式
    @Test
    public void testCallback1() {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            System.out.println("清洗大米");
            return "淘米完毕";
        }).thenApplyAsync(result -> {
            //既有入参也有返回值
            System.out.println(result + "，开始煮米饭");
            return "米饭煮好了";
        }).thenAcceptAsync(result -> {
            //有入参无返回值
            System.out.println(result + "，再整个小菜");
        }).thenRunAsync(() -> {
            //无入参无返回值
            System.out.println("晚饭搞定，可以吃了");
        });
    }


    //第二种接续方式，多个CompletableFuture的接续
    @Test
    public void testCallback2() {
        CompletableFuture rice = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始制作米饭，并获得煮熟的米饭");
            return "煮熟的米饭";
        });
        //煮米饭的同时呢，我又做了牛奶
        CompletableFuture mike = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始热牛奶，并获得加热的牛奶");
            return "加热的牛奶";
        });
        // 我想两个都好了，才吃早饭，thenCombineAsync有入参，有返回值
        mike.thenCombineAsync(rice, (m, r) -> {
            System.out.println("我收获了早饭：" + m + "," + r);
            return m.toString() + r;
        });
        // 有入参，无返回值
        mike.thenAcceptBothAsync(rice, (m, r) -> {
            System.out.println("我收获了早饭：" + m + "," + r);
        });
        // 无入参，无返回值
        mike.runAfterBothAsync(rice, () -> {
            System.out.println("我收获了早饭");
        });
        // 或者直接连接两个CompletableFuture
        rice.thenComposeAsync(r -> CompletableFuture.supplyAsync(() -> {
            System.out.println("开始煮牛奶");
            System.out.println("同时开始煮米饭");
            return "mike";
        }));
    }

    //第三种接续方式，只想做结果处理，也没有其他的接续动作，并且我们想要判断异常的情况
    @Test
    public void testCallback3() {
//        whenCompleteAsync：处理完成或异常，无返回值
//        handleAsync：处理完成或异常，有返回值
        CompletableFuture.supplyAsync(()->{
            System.out.println("开始蒸米饭");
            return "煮熟的米饭";
        }).whenCompleteAsync((rich,exception)->{
            if (exception!=null){
                System.out.println("电饭煲坏了，米饭没做熟");
            }else{
                System.out.println("米饭熟了，可以吃了");
            }
        });
        // 有返回值
        CompletableFuture.supplyAsync(()->{
            System.out.println("开始蒸米饭");
            return "煮熟的米饭";
        }).handleAsync((rich,exception)->{
            if (exception!=null){
                System.out.println("电饭煲坏了，米饭没做熟");
            }else{
                System.out.println("米饭熟了，可以吃了");
            }
            return "准备冷一冷再吃米饭";
        });
        // 异常处理
        CompletableFuture.supplyAsync(()->{
            System.out.println("开始蒸米饭");
            if(true){
                throw new RuntimeException("蒸米饭出现异常");
            }
            return "煮熟的米饭";
        }).handleAsync((rich,exception)->{
            if (exception!=null){
                System.out.println("电饭煲坏了，米饭没做熟");
            }else{
                System.out.println("米饭熟了，可以吃了");
            }
            if(true){
                throw new RuntimeException("准备冷一冷再吃米饭出现异常");
            }
            return "准备冷一冷再吃米饭";
        }).exceptionally((exception)->{
            // 前置动作必须的是一个有返回值的操作，不能是那种没有返回值的
            System.out.println(exception.getMessage());
            return "";
        });
    }
}
