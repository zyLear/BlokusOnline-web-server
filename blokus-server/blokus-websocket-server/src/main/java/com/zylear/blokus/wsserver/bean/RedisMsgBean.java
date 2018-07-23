package com.zylear.blokus.wsserver.bean;

/**
 * Created by xiezongyu on 2018/7/18.
 */
public class RedisMsgBean {

    private String wxId;
    private String content;

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
