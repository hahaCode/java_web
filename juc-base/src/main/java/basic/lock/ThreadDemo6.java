package basic.lock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 解决List线程不安全问题
 *  1. 使用Vector(不推荐)
 *  2. 使用Collections工具类(不推荐)
 *  3. 使用CopyOnWriteArraylist
 */
public class ThreadDemo6 {
    public static void main(String[] args) {

        //使用Vector(不推荐)
//        List<String> list = new Vector<>();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString().substring(0, 8));
//                System.out.println(list);
//            }, "Thread_"+i).start();
//        }

        //使用Collections工具类(不推荐)
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString().substring(0, 8));
//                System.out.println(list);
//            }, "Thread_"+i).start();
//        }

        //使用CopyOnWriteArraylist
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "Thread_"+i).start();
        }
    }
}
