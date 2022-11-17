package basic.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        //创建阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        //第一组方法  aad  ---  remove  --- element   队列满或空时抛异常

        //第二组方法 offer(成功返回true, 失败返回false)  --- poll(成功时返回队列的元素, 队列为空时返回null)  ---  peek

        //第三组方法 put --- take    超过队列容量或者为空时会阻塞

        //第四组方法  offer  --- poll  在3的基础上，可以设置超时时间
    }
}
