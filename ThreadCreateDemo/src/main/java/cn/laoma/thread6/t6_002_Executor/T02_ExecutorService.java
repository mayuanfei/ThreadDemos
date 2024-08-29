package cn.laoma.thread6.t6_002_Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: ThreadDemos
 * @description: 集成自Executor，但是比Executor使用更广泛的子类接口
 * 它提供了线程生命周期管理的许多方法。比如submit把任务提交给线程池，
 * 采用异步的方式获得线程执行结果。
 * 这里看下ExecutorService接口的方法。了解下有哪些方法即可。
 * @author: 老马
 * @create: 2021-10-05 16:52
 **/
public class T02_ExecutorService {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
    }
}
