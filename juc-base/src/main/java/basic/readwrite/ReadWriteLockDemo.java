package basic.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 读写锁
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {

        //添加写锁
        rwlock.writeLock().lock();

        System.out.println(Thread.currentThread().getName() + " is writing..." + key);
        try {
            TimeUnit.MICROSECONDS.sleep(300);

            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " writing is done..." + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放写锁
            rwlock.writeLock().unlock();
        }
    }

    public Object get(String key) {

        //添加读锁
        rwlock.readLock().lock();

        Object o = null;
        try {
            System.out.println(Thread.currentThread().getName() + " is reading..." + key);
            TimeUnit.MICROSECONDS.sleep(300);

            o = map.get(key);
            System.out.println(Thread.currentThread().getName() + " reading is done..." + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwlock.readLock().unlock();
        }
        return o;
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //创建线程放数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num+"", num);
            }, "Thread_Put_" + String.valueOf(i)).start();
        }

        //创建线程取数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num+"");
            }, "Thread_Get_" + String.valueOf(i)).start();
        }
    }
}
