package basic.sync;

/**
 * 虚假唤醒问题
 */
class Share2 {

    private int number = 0;


    public synchronized void incr() throws InterruptedException {
        //判断，干活，通知

        //虚假唤醒的原因 wait 方法没有在 while 循环中使用
        while (number != 0) {
            wait();
        }

        number++;
        System.out.println(Thread.currentThread().getName()+"::"+number);

        notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        //判断，干活，通知

        while (number != 1) {
            wait();
        }

        number--;
        System.out.println(Thread.currentThread().getName()+"::"+number);

        notifyAll();
    }

}

public class ThreadDemo2{
    public static void main(String[] args) {
        Share2 share = new Share2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread_A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread_B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread_C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread_D").start();

    }
}