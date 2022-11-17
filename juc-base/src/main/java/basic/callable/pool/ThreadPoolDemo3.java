package basic.callable.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可扩容线程池
 */
public class ThreadPoolDemo3 {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 20; i++) {
                cachedThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "正在办理业务....");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cachedThreadPool.shutdown();
        }
    }
}
