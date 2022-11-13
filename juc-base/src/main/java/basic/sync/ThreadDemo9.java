package basic.sync;

/**
 * 死锁事例子
 *
 * 查看是否出现死锁可以借用工具：
 * 1.jsp    类似 ps -ef     jps -l  查看进程号
 * 2.jstack     jstack <进程号>
 */
public class ThreadDemo9 {

    static Object a = new Object();
    static Object b = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + " 持有锁a, 试图获取锁b");
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + " 成功获取锁b");
                }
            }
        }, "Thread_A").start();

        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + " 持有锁b, 试图获取锁a");
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + " 成功获取锁a");
                }
            }
        }, "Thread_B").start();
    }
}
