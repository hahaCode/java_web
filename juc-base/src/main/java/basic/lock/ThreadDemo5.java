package basic.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 演示List线程不安全
 */
public class ThreadDemo5 {

    public static void main(String[] args) {

        //java.util.ConcurrentModificationException
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread_"+i).start();
        }
    }
}
