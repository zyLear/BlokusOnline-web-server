package com.zylear.blokus.wsserver.bean.base;

import io.netty.channel.Channel;


/**
 * Created by xiezongyu on 2018/7/10.
 */
public class TransferBean {
    public TransferBean() {

    }


    public TransferBean(Channel channel, MessageBean messageBean) {
        this.channel = channel;
        this.messageBean = messageBean;
    }

    private Channel channel;
    private MessageBean messageBean;


    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }


    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }


    @Override
    public String toString() {
        return "TransferBean{" +
                "channel=" + channel +
//                ", handle=" + handle +
                ", messageBean=" + messageBean +
                '}';
    }
}
