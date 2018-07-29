package com.zylear.blokus.wsserver.bean.transfer;

import com.zylear.blokus.wsserver.enums.GameType;

/**
 * Created by xiezongyu on 2018/7/28.
 */
public class StartBlokusMsg {

    private Integer color;
    private Integer gameType;

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
}
