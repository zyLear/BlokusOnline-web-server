package com.zylear.blokus.wsserver.bean.transfer.base;

/**
 * Created by xiezongyu on 2018/7/27.
 */
public class ResponseMsg {
    private Integer errorCode;
    private String errorMsg;

    public static final ResponseMsg SUCCESS = new ResponseMsg(0, "success");

    public static final ResponseMsg FAIL = new ResponseMsg(1, "fail");


    public ResponseMsg(ResponseMsg responseMsg) {
        this.errorCode = responseMsg.errorCode;
        this.errorMsg = responseMsg.errorMsg;
    }

    public ResponseMsg() {
    }

    public ResponseMsg(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
