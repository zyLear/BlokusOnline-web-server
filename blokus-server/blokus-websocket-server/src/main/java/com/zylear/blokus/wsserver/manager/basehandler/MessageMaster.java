package com.zylear.blokus.wsserver.manager.basehandler;



import com.zylear.blokus.wsserver.bean.transfer.base.TransferBean;
import com.zylear.blokus.wsserver.manager.MessageManager;
import com.zylear.blokus.wsserver.queue.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiezongyu on 2018/2/2.
 */
@Component
public class MessageMaster {

    private static final Logger logger = LoggerFactory.getLogger(MessageMaster.class);

    private final int maxThreadCount = 100;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ExecutorService executorServices = Executors.newFixedThreadPool(maxThreadCount);

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

    public void getMessageLoop() {
        while (true) {
            if (currentRunningCount.get() < maxThreadCount) {
                TransferBean transferBean = MessageQueue.getInstance().take();
                if (transferBean != null) {
                    int count = currentRunningCount.incrementAndGet();
                    logger.info("begin handle msg. current:{}", count);
                    executorServices.submit(new MessageWorker(messageManager, transferBean));
                } else {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    @Autowired
    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }
}
