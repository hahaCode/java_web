package basic.lock;


import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 演示HashSet线程不安全
 *
 * 解决办法：
 * 1. CopyOnWriteArraySet
 */
public class ThreadDemo7 {
    public static void main(String[] args) {

        // ConcurrentModificationException
        //Set<String> set = new HashSet<>();

        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, "Thread_"+i).start();
        }
    }
}
