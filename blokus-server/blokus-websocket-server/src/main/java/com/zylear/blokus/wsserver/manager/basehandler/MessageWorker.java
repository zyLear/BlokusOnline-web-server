package com.zylear.blokus.wsserver.manager.basehandler;


import com.zylear.blokus.wsserver.bean.base.TransferBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiezongyu on 2018/2/2.
 */
public class MessageWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MessageWorker.class);

    private MessageHandler<TransferBean, List<TransferBean>> messageHandler;
    private TransferBean transferBean;

    public MessageWorker(MessageHandler messageHandler, TransferBean transferBean) {
        this.messageHandler = messageHandler;
        this.transferBean = transferBean;
    }

    @Override
    public void run() {
        try {
            if (messageHandler != null) {
                List<TransferBean> responses = new LinkedList<>();
//                if(transferBean.getHandle()) {
                    messageHandler.handle(transferBean, responses);
//                }else {
//                    responses.add(transferBean);
//                }
                messageHandler.send(responses);
            }
        } catch (Exception e) {
            logger.info("handle msg exception", e);
        } finally {
            int count = MessageMaster.currentRunningCount.decrementAndGet();
            logger.info("handle msg end. current:{}", count);
        }

    }
}
