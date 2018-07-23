package com.zylear.blokus.wsserver.bean;

/**
 * Created by lichao01 on 2018/7/10.
 */
public class WxLoginMsg {

    public WxLoginMsg() {
    }

    public WxLoginMsg(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private String imgUrl;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "WxLoginMsg{" +
                "imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
