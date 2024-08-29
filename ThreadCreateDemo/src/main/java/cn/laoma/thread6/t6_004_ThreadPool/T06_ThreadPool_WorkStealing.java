package cn.laoma.thread6.t6_004_ThreadPool;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: ThreadDemos
 * @description: WorkstealingPool
 * 它会通过工作窃取的方式，使得多核的 CPU 不会闲置，总会有活着的线程让 CPU 去运行。
 * 它基于工作窃取算法，其中任务可以生成其他较小的任务，这些任务将添加到并行处理线程的队列中。
 * 如果一个线程完成了工作并且无事可做，则可以从另一线程的队列中“窃取”工作。
 * 这里看下原理图-线程池.jpg和原理图-ForkJoin线程池.png
 * @author: 老马
 * @create: 2021-10-15 15:09
 **/
public class T06_ThreadPool_WorkStealing {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("获得cpu处理器数量：" + Runtime.getRuntime().availableProcessors());
        ExecutorService service = Executors.newWorkStealingPool();
        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i -> (Callable<String>) () -> {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(String.format("当前【%s线程正在执行>>>】", Thread.currentThread().getName()));
            return "callable type thread task : " + i;
        }).collect(Collectors.toList());

        //执行任务
        service.invokeAll(callableList).stream().map(futureTask -> {
            try {
                return futureTask.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(System.out::println);
    }
}
