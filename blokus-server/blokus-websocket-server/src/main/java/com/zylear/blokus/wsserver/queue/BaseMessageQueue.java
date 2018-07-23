package com.zylear.blokus.wsserver.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 28444
 * @date 2018/2/1.
 */
public class BaseMessageQueue<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseMessageQueue.class);

    /**
     * 消息队列
     */
    private final BlockingQueue<T> queue = new LinkedBlockingQueue<T>();

    /**
     * 阻塞
     */
    public T take() {
//        return queue.poll();
        try {
            return queue.take();
        } catch (InterruptedException e) {
            logger.info("take message from queue fail. ", e);
        }
        return null;
    }

    /**
     * 阻塞,消息不能丢掉
     */
    public void put(T t) {
        try {
            queue.put(t);
        } catch (Exception e) {
            logger.info("put message into queue fail. ", e);
        }
    }

    public int getQueueSize() {
        return queue.size();
    }

}