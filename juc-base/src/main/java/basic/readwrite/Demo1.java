package basic.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级
 */
public class Demo1 {
    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

        Map<String, String> map = new HashMap<>();

        //获取写锁
        writeLock.lock();
        map.put("aaa", "hello");
        System.out.println("i am writing....");
        map.put("bbb", "good");

        //获取读锁
        readLock.lock();
        System.out.println(map.get("aaa"));

        //释放写锁
        writeLock.unlock();

        //释放读锁
        readLock.unlock();
    }
}
