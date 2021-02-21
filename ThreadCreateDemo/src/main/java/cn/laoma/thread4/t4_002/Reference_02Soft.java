package cn.laoma.thread4.t4_002;
import lombok.SneakyThrows;
import java.lang.ref.SoftReference;

/**
* @Description: 软引用
*  当内存不足时回收
 *  JVM设置20M内存：-Xms20M -Xmx20M
 *  适合用于缓存
* @author: 老马
* @Date: 2021/2/21 14:32
*/
public class Reference_02Soft {
    @SneakyThrows
    public static void main(String[] args) {
        //分配了10M
        SoftReference<byte[]> soft = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(soft.get());
        System.gc();
        Thread.sleep(500);
        System.out.println(soft.get());
        //再次分配内存，此时内存不足。
        byte[] newbyte = new byte[1024*1024*11];
        System.out.println(soft.get());
    }
}
