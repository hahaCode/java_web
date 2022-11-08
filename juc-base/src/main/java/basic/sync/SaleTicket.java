package basic.sync;


class Ticket{
    private int number = 30;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "：卖出：" + number-- + "; 剩余： "+ number);
        }
    }
}

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        for (int j = 0; j < 3; j++) {
            new Thread(() -> {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }, "Thread_"+(j+1)).start();
        }
    }
}
