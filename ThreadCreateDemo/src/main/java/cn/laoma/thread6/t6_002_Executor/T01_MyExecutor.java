package cn.laoma.thread6.t6_002_Executor;

import java.util.concurrent.Executor;

/**
 * @program: ThreadDemos
 * @description: 这里主要看Executor【执行者】接口。把任务的定义和运行进行分开
 * @author: 老马
 * @create: 2021-10-05 10:56
 **/
public class T01_MyExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        new T01_MyExecutor().execute(()-> System.out.println("myexecutor"));
    }
}