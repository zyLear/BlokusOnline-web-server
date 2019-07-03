package com.zylear.blokus.wsserver.manager.basehandler;


import com.zylear.blokus.wsserver.bean.transfer.base.TransferBean;
import com.zylear.blokus.wsserver.manager.MessageManager;
import com.zylear.blokus.wsserver.queue.MessageQueue;
import com.zylear.blokus.wsserver.thread.CountableThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiezongyu on 2018/2/2.
 */
@Component
public class MessageMaster {

    private static final Logger logger = LoggerFactory.getLogger(MessageMaster.class);

    private final int maxThreadCount = 100;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ExecutorService executorServices = Executors.newFixedThreadPool(maxThreadCount);

    private CountableThreadPool countableThreadPool = new CountableThreadPool(maxThreadCount, executorServices);


    private ReentrantLock newCommandLock = new ReentrantLock();
    private Condition newCommandLockCondition = newCommandLock.newCondition();

    public static AtomicInteger currentRunningCount = new AtomicInteger(0);

    private MessageManager messageManager;

    @PostConstruct
    public void start() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                getMessageLoop();
            }
        });
    }

//    public void getMessageLoop() {
//        while (true) {
//            if (currentRunningCount.get() < maxThreadCount) {
//                TransferBean transferBean = MessageQueue.getInstance().take();
//                if (transferBean != null) {
//                    int count = currentRunningCount.incrementAndGet();
//                    logger.info("begin handle msg. current:{}", count);
//                    executorServices.submit(new MessageWorker(messageManager, transferBean));
//                } else {
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                    }
//                }
//            }else {
//                //wait until work thread decrement reentryLock
//            }
//        }
//    }


    public void addCommand(TransferBean transferBean) {
        MessageQueue.getInstance().put(transferBean);
        signalNewCommand();
    }


    private void getMessageLoop() {
        while (true) {
//            if (currentRunningCount.get() < maxThreadCount) {
            try {
                TransferBean transferBean = MessageQueue.getInstance().poll();
                if (transferBean == null) {
                    waitCommand();
                } else {
//                    int count = currentRunningCount.incrementAndGet();
//                    logger.info("begin handle msg. current:{}", count);
                    countableThreadPool.submit(new MessageWorker(messageManager, transferBean));
//                    executorServices.submit(new MessageWorker(messageManager, transferBean));
                }


            } catch (Exception e) {
                logger.info("getMessageLoop exception. ", e);
            }
//            } else {
//                wait until work thread decrement reentryLock
//            }
        }
    }

    private void signalNewCommand() {
        try {
            newCommandLock.lock();
            newCommandLockCondition.signalAll();
        } finally {
            newCommandLock.unlock();
        }
    }


    private void signalNewCommandTest() {
        try {
            newCommandLock.lock();
            System.out.println("signal start ");
            newCommandLockCondition.signalAll();
            System.out.println("signal end ");
            Thread.sleep(3000);
            System.out.println("signal sleep end ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            newCommandLock.unlock();
        }
    }


    private void waitCommand() {
        newCommandLock.lock();
        try {
            System.out.println("wait start");
            newCommandLockCondition.await();
            System.out.println("wait end");
            Thread.sleep(3000);
            System.out.println("wait sleep end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            newCommandLock.unlock();
        }
    }

    private void waitCommandTest() {
        newCommandLock.lock();
        try {
            newCommandLockCondition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            newCommandLock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final MessageMaster messageMaster = new MessageMaster();
        for (int i = 0; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    messageMaster.waitCommandTest();
                }
            }.start();
        }
        Thread.sleep(2000);
        messageMaster.signalNewCommandTest();
    }


    @Autowired
    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }
}
