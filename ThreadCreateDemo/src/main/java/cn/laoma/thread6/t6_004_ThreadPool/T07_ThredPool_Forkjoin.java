package cn.laoma.thread6.t6_004_ThreadPool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @program: ThreadDemos
 * @description: ForkJion线程池
 * 大一个大任务，分成很多的分支进行计算。最后再合并在一起。
 * 这里看下ForkJoin线程池.jpg图。和大数据中的MapReduce一致
 * @author: 老马
 * @create: 2021-10-16 12:46
 **/
public class T07_ThredPool_Forkjoin {
    static int[] nums = new int[1000000];
    //50000个数为最小单位，不用再分了。
    static final  int MAX_NUM = 50000;
    static Random random = new Random();
    static {
        //初始化0-99的随机数
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }
        long startTime = System.currentTimeMillis();
        System.out.println(Arrays.stream(nums).sum());
        long endTime = System.currentTimeMillis();
        System.out.println("循环用时：" + (endTime-startTime));

    }

    //RecursiveTask递归任务,还有一个没有返回值的RecursiveAction任务
    static class AddTask extends RecursiveTask<Long> {
        int start, end;
        AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if((end-start) <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            }else {
                //任务分解
                int middle = (start + end)/2;
                AddTask left = new AddTask(start, middle);
                AddTask right = new AddTask(middle, end);
                left.fork();
                right.fork();
                //把两个小任务的结果累加
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        AddTask addTask = new AddTask(0, nums.length);
        long startTime = System.currentTimeMillis();
        Long result = forkJoinPool.invoke(addTask);
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("ForkJoin用时：" + (endTime-startTime));
    }
}
