package com.zylear.blokus.wsserver.bean.transfer.base;

import com.zylear.blokus.wsserver.constant.MsgType;
import com.zylear.blokus.wsserver.util.JsonUtil;

/**
 * Created by xiezongyu on 2018/7/17.
 */
public class MessageBean {
    public static final MessageBean LOGIN_SUCCESS = new MessageBean(MsgType.LOGIN_RESPONSE, JsonUtil.toJSONString(ResponseMsg.SUCCESS));

    public static final MessageBean LOGIN_FAIL = new MessageBean(MsgType.LOGIN_RESPONSE, JsonUtil.toJSONString(ResponseMsg.FAIL));

    public static final MessageBean CREATE_ROOM_FAIL = new MessageBean(MsgType.CREATE_ROOM_RESPONSE, JsonUtil.toJSONString(ResponseMsg.FAIL));

    public static final MessageBean JOIN_ROOM_FAIL = new MessageBean(MsgType.JOIN_ROOM_RESPONSE, JsonUtil.toJSONString(ResponseMsg.FAIL));

    public static final MessageBean LEAVE_ROOM_SUCCESS = new MessageBean(MsgType.LEAVE_ROOM_RESPONSE, JsonUtil.toJSONString(ResponseMsg.SUCCESS));

    public static final MessageBean LEAVE_ROOM_FAIL = new MessageBean(MsgType.LEAVE_ROOM_RESPONSE, JsonUtil.toJSONString(ResponseMsg.FAIL));

    public static final MessageBean START_BLOKUS = new MessageBean(MsgType.START_BLOKUS, "");

    public static final MessageBean START_BLOKUS_TWO_PEOPLE = new MessageBean(MsgType.START_BLOKUS_TWO_PEOPLE, "");

    private Integer msgType;
    private String content;

    public MessageBean() {
    }

    public MessageBean(Integer msgType, String content) {
        this.msgType = msgType;
        this.content = content;
    }

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
