package basic.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new Thread1(), "AA").start();

        FutureTask<String> futureTask = new FutureTask<>(new Thread2());

//        FutureTask<String> futureTask = new FutureTask<>(() -> {
//            System.out.println(Thread.currentThread().getName()+ " come in callable");
//            return "good";
//        });

        new Thread(futureTask, "BB").start();

        while (!futureTask.isDone()) {
            System.out.println(Thread.currentThread().getName()+ " is waiting....");
        }
        System.out.println(futureTask.get());

        System.out.println(Thread.currentThread().getName()+ " is over!");
    }
}

class Thread1 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " is running");
    }
}

class Thread2 implements Callable {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+ " come in callable");
        return "hello";
    }
}
