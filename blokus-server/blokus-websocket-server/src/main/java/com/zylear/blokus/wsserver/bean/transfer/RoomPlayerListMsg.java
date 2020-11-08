package com.zylear.blokus.wsserver.bean.transfer;

import java.util.List;

/**
 * Created by xiezongyu on 2018/7/27.
 */
public class RoomPlayerListMsg {

    private List<RoomPlayerInfoMsg> playerInfoMsgList;

    public List<RoomPlayerInfoMsg> getPlayerInfoMsgList() {
        return playerInfoMsgList;
    }

    public void setPlayerInfoMsgList(List<RoomPlayerInfoMsg> playerInfoMsgList) {
        this.playerInfoMsgList = playerInfoMsgList;
    }

    public static class RoomPlayerInfoMsg{
        private String account;
        private Integer color;
        private Boolean isReady;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public Integer getColor() {
            return color;
        }

        public void setColor(Integer color) {
            this.color = color;
        }

        public Boolean getIsReady() {
            return isReady;
        }

        public void setIsReady(Boolean isReady) {
            this.isReady = isReady;
        }
    }
}
