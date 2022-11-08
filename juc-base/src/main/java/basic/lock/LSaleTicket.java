package basic.lock;

import java.util.concurrent.locks.ReentrantLock;

class LTicket{
    private int number = 30;

    private final ReentrantLock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "：卖出：" + number-- + "; 剩余： "+ number);
            }
        } finally {
            lock.unlock();
        }

    }
}

public class LSaleTicket {
    public static void main(String[] args) {

        LTicket ticket = new LTicket();
        for (int j = 0; j < 3; j++) {
            new Thread(() -> {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }, "Thread_"+(j+1)).start();
        }
    }

}
