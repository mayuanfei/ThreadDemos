package cn.laoma.thread6.t6_004_ThreadPool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @program: ThreadDemos
 * @description: 并行流操作。
 * 1.底层也是用的ForkJoin线程池
 * 2.parallelStream并行执行是无序的。
 * 3.parallelStream是线程不安全的
 * @author: 老马
 * @create: 2021-10-17 09:11
 **/
public class T08_ThreadPool_ParallelStream {
    List<Person> persons = new ArrayList<>();
    @Data
    @AllArgsConstructor
    class Person {
        int id;
        String name;
        int age;
    }

    //构造数据
    @Before
    public void init() {
        for (int i = 0; i < 5; i++) {
            persons.add(new Person(i, "人"+i, i));
        }
    }

    //测试下循环
    @SneakyThrows
    @Test
    public void testLoop() {
        long start = System.currentTimeMillis();
        //普通循环
        for(Person person : persons) {
            Thread.sleep(1000);
            System.out.println(person);
        }
        long end = System.currentTimeMillis();
        System.out.println("普通循环用时：" + (end - start));

        //流循环
        start = System.currentTimeMillis();
        persons.stream().forEach(person -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(person);
        });
        end = System.currentTimeMillis();
        System.out.println("流循环用时：" + (end - start));

        //并行流
        start = System.currentTimeMillis();
        persons.parallelStream().forEach(person -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(person);
        });
        end = System.currentTimeMillis();
        System.out.println("并行流循环用时：" + (end - start));
    }

    //测试下线程安全
    @Test
    public void testUnsafe() {
        List<Integer> list = new ArrayList<>();
        List<Integer> listParallel = new ArrayList<>();
        //List<Integer> listParallel = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0, 1000).forEach(list::add);
        IntStream.range(0, 1000).parallel().forEach(listParallel::add);
        System.out.println(list.size());
        System.out.println(listParallel.size());
    }
}
