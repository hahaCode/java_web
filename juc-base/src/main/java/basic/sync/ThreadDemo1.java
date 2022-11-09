package basic.sync;

/**
 * 实现两个线程对 number 进行操作
 * 当number == 0 时  A线程使其 +1
 * 当number !=0 时   B线程使其 -1
 */
class Share {

    private int number = 0;


    public synchronized void incr() throws InterruptedException {
        //判断，干活，通知

        if (number != 0) {
            wait();
        }

        number++;
        System.out.println(Thread.currentThread().getName()+"::"+number);

        notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        //判断，干活，通知

        if (number != 1) {
            wait();
        }

        number--;
        System.out.println(Thread.currentThread().getName()+"::"+number);

        notifyAll();
    }

}

public class ThreadDemo1{
    public static void main(String[] args) {
        Share share = new Share();

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

    }
}