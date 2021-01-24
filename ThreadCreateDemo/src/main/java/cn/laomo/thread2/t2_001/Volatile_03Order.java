package cn.laomo.thread2.t2_001;

/**
 * @program: ThreadDemos
 * @description: 看下Object对象在字节码中的语句
 * 1.new :申请内存【默认值】
 * 2.invokespecial： 进行初始化
 * 3.把内存地址赋值给栈中的变量obj
 * @author: 老马
 * @create: 2021-01-24 20:33
 **/
public class Volatile_03Order {

    public static void main(String[] args) {
        Object obj = new Object();
    }
}
