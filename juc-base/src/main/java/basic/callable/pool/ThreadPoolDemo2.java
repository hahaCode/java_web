package basic.callable.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个池子一个线程
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        try {
            for (int i = 0; i < 10; i++) {
                singleThreadExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "正在办理业务....");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            singleThreadExecutor.shutdown();
        }
    }
}
