package com.zylear.blokus.wsserver.bean;

/**
 * Created by xiezongyu on 2018/7/17.
 */
public class RegisterRespMsg {

    public static final RegisterRespMsg SUCCESS_RESPONSE = new RegisterRespMsg(0, "success");
    public static final RegisterRespMsg ERROR_RESPONSE = new RegisterRespMsg(1, "error");

    public RegisterRespMsg(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public RegisterRespMsg() {
    }

    private Integer errCode;
    private String errMsg;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
