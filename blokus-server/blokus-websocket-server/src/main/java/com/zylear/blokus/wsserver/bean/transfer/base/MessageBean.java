package com.zylear.blokus.wsserver.bean.transfer.base;

/**
 * Created by xiezongyu on 2018/7/17.
 */
public class MessageBean {
    private Integer msgType;
    private String content;

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "msgType=" + msgType +
                ", content='" + content + '\'' +
                '}';
    }
}
