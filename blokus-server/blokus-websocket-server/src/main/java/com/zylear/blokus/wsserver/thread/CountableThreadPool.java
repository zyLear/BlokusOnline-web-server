package com.zylear.blokus.wsserver.thread;

import com.zylear.blokus.wsserver.manager.basehandler.MessageWorker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiezongyu on 2018/11/1.
 */
public class CountableThreadPool {

    public CountableThreadPool(Integer threadCount, ExecutorService executorService) {
        this.threadCount = threadCount;
        this.executorService = executorService;
    }

    private Integer threadCount;
    private ExecutorService executorService;
    private ReentrantLock threadPoolReentrantLock = new ReentrantLock();
    private Condition threadPoolReentrantLockCondition = threadPoolReentrantLock.newCondition();

    public static AtomicInteger currentRunningCount = new AtomicInteger(0);


    public void submit(final Runnable runnable) {

        try {
            if (currentRunningCount.get() >= threadCount) {
                threadPoolReentrantLock.lock();
                try {
                    while (currentRunningCount.get() >= threadCount) {
                        try {
                            threadPoolReentrantLockCondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    threadPoolReentrantLock.unlock();
                }

                currentRunningCount.incrementAndGet();
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            runnable.run();
                        } finally {
                            try {
                                threadPoolReentrantLock.lock();
                                currentRunningCount.decrementAndGet();
                                threadPoolReentrantLockCondition.signal();
                            } finally {
                                threadPoolReentrantLock.unlock();
                            }
                        }
                    }
                });


            } else {
                waitConsume();
            }


        } finally {
        }
    }

    public void waitConsume() {
        try {
            threadPoolReentrantLock.lock();
            threadPoolReentrantLockCondition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {

        }
    }

    public void signalIdle() {

    }
}
