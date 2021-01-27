package cn.laoma.thread2.t2_003;

import java.util.concurrent.Phaser;

/**
 * @program: ThreadDemos
 * @description: 阶段栅栏
 * 每个阶段都是一个CyclicBarrier，都满足条件进入下一个阶段
 * 例子：婚礼现场
 * @author: 老马
 * @create: 2021-01-27 21:37
 **/
public class Lock_09Phaser {
    static MarriagePhaser phaser = new MarriagePhaser(7);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Persion("P" + i)).start();
        }
        new Thread(new Persion("新郎")).start();
        new Thread(new Persion("新娘")).start();
    }

    //结婚阶段器
    static class MarriagePhaser extends Phaser {
        public MarriagePhaser(int parties) {
            super(parties);
        }
        @Override
        //phase:当前第几个几段；registeredParties：参加这个阶段的线程数
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0 :
                    System.out.println("所有人到达婚礼现场" + registeredParties);
                    System.out.println("-----------------------------");
                    return false;
                case 1 :
                    System.out.println("所有人吃完了" + registeredParties);
                    System.out.println("-----------------------------");
                    return false;
                case 2 :
                    System.out.println("所有人离开了" + registeredParties);
                    System.out.println("-----------------------------");
                    return false;
                case 3 :
                    System.out.println("新娘新郎洞房花烛夜" + registeredParties);
                    System.out.println("-----------------------------");
                    return false;
                case 4 :
                    System.out.println("新娘生猴子" + registeredParties);
                    System.out.println("-----------------------------");
                    return true;
                default:
                    return true;
            }
        }
    }

    //与会人
    static class Persion extends Thread {
        String name;
        public Persion(String name) {
            this.name = name;
        }
        public void arrived() {
            try {Thread.sleep((long) (Math.random() * 3000));} catch (InterruptedException e) {}
            System.out.printf("%s 到达婚礼现场\n", name);
            phaser.arriveAndAwaitAdvance();
        }
        public void eat() {
            try {Thread.sleep((long) (Math.random() * 3000));} catch (InterruptedException e) {}
            System.out.printf("%s 都吃完了\n", name);
            phaser.arriveAndAwaitAdvance();
        }
        public void leave() {
            try {Thread.sleep((long) (Math.random() * 3000));} catch (InterruptedException e) {}
            System.out.printf("%s 离开婚礼现场\n", name);
            phaser.arriveAndAwaitAdvance();
        }
        public void onWeddingNight() {
            if(name.equals("新郎") || name.equals("新娘")) {
                try {Thread.sleep((long) (Math.random() * 3000));} catch (InterruptedException e) {}
                System.out.printf("%s 洞房花烛夜\n", name);
                phaser.arriveAndAwaitAdvance();
            }else {
                phaser.arriveAndDeregister();
            }
        }
        public void havaMonkey() {
            if(name.equals("新娘")) {
                try {Thread.sleep((long) (Math.random() * 3000));} catch (InterruptedException e) {}
                System.out.printf("%s 生猴子\n", name);
                phaser.arriveAndAwaitAdvance();
            }else {
                if(name.equals("新郎")) {
                    phaser.arriveAndDeregister();
                }
            }
        }
        @Override
        public void run() {
            arrived();
            eat();
            leave();
            onWeddingNight();
            havaMonkey();
        }
    }
}
