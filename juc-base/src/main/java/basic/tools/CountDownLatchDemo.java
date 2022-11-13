package basic.tools;

/**
 * 辅助类 --- 减少计数
 *
 * 在完成一组或正在其他线程中执行的操作之前, 它允许一个或多个线程一直等待
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号同学走了！");
                countDownLatch.countDown();
            }, "Thread_" + i).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 锁门了！");
    }
}
