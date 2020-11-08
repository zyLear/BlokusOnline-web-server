package com.zylear.blokus.wsserver.bean.transfer;

import com.zylear.blokus.wsserver.enums.GameType;

import java.util.List;

/**
 * Created by xiezongyu on 2018/7/28.
 */
public class StartBlokusMsg {

    private String account;
    private Integer color;
    private Integer gameType;

    private List<PlayerInfoMsg> playerList;

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

    public Integer getGameType() {
        return gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }

    public List<PlayerInfoMsg> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<PlayerInfoMsg> playerList) {
        this.playerList = playerList;
    }

    public static class PlayerInfoMsg {
        private String account;
        private Integer color;

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
    }

}
