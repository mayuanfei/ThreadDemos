package cn.laoma.thread6.t6_003_Future;

/**
 * @program: ThreadDemos
 * @description: CompletableFuture方法记忆规则
 * @author: 老马
 * @create: 2021-10-06 16:00
 **/
public class T04_CompletableFuture_remember {
    /*********************************************************************
     * 执行类方法
     ********************************************************************/
//    runAsync 异步执行，无返回值
//    supplyAsync 异步执行，有返回值
//    anyOf 任意一个执行完成，就可以进行下一步动作
//    allOf 全部完成所有任务，才可以进行下一步任务runAsync 异步执行，无返回值

    /*********************************************************************
     * 状态取值类方法
     ********************************************************************/
//    join 合并结果，等待
//    get 合并等待结果，可以增加超时时间;get和join区别，join只会抛出unchecked异常， get会返回具体的异常
//    getNow 如果结果计算完成或者异常了，则返回结果或异常；否则，返回valueIfAbsent的值
//    isCancelled 是否取消
//    isCompletedExceptionally 是否异常
//    isDone 是否完成

    /*********************************************************************
     * 控制类方法 用于主动控制CompletableFuture的完成行为
     ********************************************************************/
//    complete  主动完成
//    completeExceptionally 主动抛出异常
//    cancel 主动取消

    /*********************************************************************
     * 接续类方法
     ********************************************************************/
//    thenApply, thenApplyAsync
//    thenAccept, thenAcceptAsync
//    thenRun, thenRunAsync
//    thenCombine, thenCombineAsync
//    thenAcceptBoth, thenAcceptBothAsync
//    runAfterBoth, runAfterBothAsync
//    applyToEither, applyToEitherAsync
//    acceptEither, acceptEitherAsync
//    runAfterEither, runAfterEitherAsync
//    thenCompose, thenComposeAsync
//    whenComplete, whenCompleteAsync
//    handle, handleAsync
//    exceptionally


    /*********************************************************************
     * 记忆规则
     * 1.以Async结尾的方法，都是异步方法，对应的没有Async则是同步方法，一般都是一个异步方法对应一个同步方法。
     * 2.以Async后缀结尾的方法，都有两个重载的方法，一个是使用内容的forkjoin线程池，一种是使用自定义线程池
     * 3.以run开头的方法，其入口参数一定是无参的，并且没有返回值，类似于执行Runnable方法。
     * 4.以supply开头的方法，入口也是没有参数的，但是有返回值
     * 5.以Accept开头或者结尾的方法，入口参数是有参数，但是没有返回值
     * 6.以Apply开头或者结尾的方法，入口有参数，有返回值
     * 7.带有either后缀的方法，表示谁先完成就消费谁
     ********************************************************************/
}
