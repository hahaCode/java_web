package basic.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 演示HashMap线程不安全
 *
 * 解决办法：
 * 1. ConcurrentHashMap
 */
public class ThreadDemo8 {
    public static void main(String[] args) {

        // ConcurrentModificationException
        //Map<String, String> map = new HashMap<>();

        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                map.put(key, UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, "Thread_"+i).start();
        }
    }
}
