package cn.laoma.thread6.t6_003_Future;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @program: ThreadDemos
 * @description: 结合Java 8 的stream
 * 这里模拟从wfjwbl表中通过交易日期+参考号获得数据
 * @author: 老马
 * @create: 2021-10-07 18:57
 **/
public class T08_CompletableFuture_withstream {
    static List<String> searchList ;
    static List<WfjWbl> wfjWbls;

    @AllArgsConstructor
    @Data
    static class WfjWbl {
        Integer id;
        String trandate;
        String referno;
        Long amout;
    }

    static {
        searchList = new ArrayList<>();
        searchList.add("20211007000000000001");
        searchList.add("20211007000000000002");
        searchList.add("20211007000000000004");
        wfjWbls = new ArrayList<>();
        wfjWbls.add(new WfjWbl(1, "20211007", "000000000001", 111L));
        wfjWbls.add(new WfjWbl(2, "20211007", "000000000002", 112L));
        wfjWbls.add(new WfjWbl(3, "20211007", "000000000003", 113L));
    }

    static  WfjWbl getWfjWbl(String search) {
        return wfjWbls.stream().filter(wfjwbl -> (wfjwbl.getTrandate() + wfjwbl.getReferno()).equals(search)).findFirst().orElse(null);
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 使用自定义的线程池发出这个请求，获得相关的数据
        List<CompletableFuture<WfjWbl>> futures = searchList.stream()
                .map(item ->
                        CompletableFuture.supplyAsync(() -> getWfjWbl(item)
                                , executorService)
                ).collect(Collectors.toList());
        // 结合Stream轻松实现多线程并发处理
        List<WfjWbl> wfjWbls = futures.stream()
                .map(CompletableFuture::join)
                .filter(item -> item != null && item.getId() != null)
                .collect(Collectors.toList());
        System.out.println(wfjWbls);
        executorService.shutdown();
    }

}
