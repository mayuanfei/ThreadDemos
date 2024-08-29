package cn.laoma.thread6.t6_004_ThreadPool;

import lombok.SneakyThrows;
import lombok.ToString;

import java.util.concurrent.*;

/**
 * @program: ThreadDemos
 * @description: 线程池。手动创建一个线程池【阿里开发手册推荐的方式】
 * @author: 老马
 * @create: 2021-10-05 12:04
 **/
public class T01_ThreadPool {

    @ToString
    static class MyTask implements Runnable {
        private int index;
        public MyTask(int idx) {
            this.index = idx;
        }
        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " task " + index);
            //阻塞线程
            System.in.read();
        }
    }

    public static void main(String[] args) {
        /*******************************************************************************
         * 线程池7个参数：
         * 1	corePoolSize	int	                        核心线程池大小
         * 2	maximumPoolSize	int	                        最大线程池大小
         * 3	keepAliveTime	long	                    线程最大空闲时间
         * 4	unit	        TimeUnit	                时间单位
         * 5	workQueue	    BlockingQueue<Runnable>	    线程等待队列
         * 6	threadFactory	ThreadFactory	            线程创建工厂
         * 7	handler	        RejectedExecutionHandler	拒绝策略
         * 四种默认的拒绝策略：
         * 1    AbortPolicy         丢弃任务并抛出RejectedExecutionException异常
         * 2    DiscardPolicy       丢弃任务，但是不抛出异常。
         * 3    DiscardOldestPolicy 丢弃队列中最老的，然后再次尝试提交新任务。
         * 4    CallerRunsPolicy    由调用线程（提交任务的线程）处理该任务
         *
         ******************************************************************************/
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4,
                            60, TimeUnit.SECONDS,
                            new ArrayBlockingQueue<>(4),
                            Executors.defaultThreadFactory(),
                            new ThreadPoolExecutor.DiscardOldestPolicy());
                            //new MyIgnorePolicy());    //采用自定义拒绝策略
        //把线程池占满
        for (int i = 0; i < 8; i++) {
            //这里大概看下execute方法。里面最重要是两个对象：HashSet的workers和BlockingQueue的workQueue
            //具体源码可以看ThreadPoolExecutor源码解析.md文件
            threadPoolExecutor.execute(new MyTask(i));
        }
        //观察此时再加一个线程,观察拒绝策略对等待队列的影响
        System.out.println(threadPoolExecutor.getQueue());
        threadPoolExecutor.execute(new MyTask(10));
        System.out.println(threadPoolExecutor.getQueue());
        threadPoolExecutor.shutdown();
    }

    //自定义拒绝策略
    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println( r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }
}