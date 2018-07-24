package com.zylear.blokus.wsserver.queue;


import com.zylear.blokus.wsserver.bean.transfer.base.TransferBean;

/**
 * @author 28444
 * @date 2018/2/1.
 */
public class MessageQueue extends BaseMessageQueue<TransferBean> {

    private static final MessageQueue INSTANCE = new MessageQueue();

    public static MessageQueue getInstance() {
        return INSTANCE;
    }
}
