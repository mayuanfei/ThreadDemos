package cn.laomo.thread2.t2_001;

/**
 * @program: ThreadDemos
 * @description: voldtile DCL-Double check lock. 如果不加volatile,会受到指令重拍的困扰
 * @author: 老马
 * @create: 2021-01-24 20:33
 **/
public class Volatile_02DCLTest {

    private static /*volatile*/ Volatile_02DCLTest instance;

    private Volatile_02DCLTest() {
    }

    public static Volatile_02DCLTest getInstance() {
        //业务代码
        if(instance == null) {
            //双重校验
            synchronized (Volatile_02DCLTest.class) {
                if(instance == null) {
                    instance = new Volatile_02DCLTest();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Volatile_02DCLTest.getInstance().hashCode())).start();
        }
    }
}
