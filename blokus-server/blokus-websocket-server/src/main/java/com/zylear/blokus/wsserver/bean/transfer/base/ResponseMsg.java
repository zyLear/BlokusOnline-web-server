package com.zylear.blokus.wsserver.bean.transfer.base;

/**
 * Created by xiezongyu on 2018/7/27.
 */
public class ResponseMsg {
    private Integer code;
    private String message;

    public static final Integer SUCC = 0;

    public static final ResponseMsg SUCCESS = new ResponseMsg(0, "success");

    public static final ResponseMsg FAIL = new ResponseMsg(1, "fail");


    public ResponseMsg(ResponseMsg responseMsg) {
        this.code = responseMsg.code;
        this.message = responseMsg.message;
    }

    public ResponseMsg() {
    }

    public ResponseMsg(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
