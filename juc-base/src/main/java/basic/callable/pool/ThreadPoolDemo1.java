package basic.callable.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个池子多个线程
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        try {
            for (int i = 0; i < 10; i++) {
                fixedThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "正在办理业务....");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fixedThreadPool.shutdown();
        }

    }
}
