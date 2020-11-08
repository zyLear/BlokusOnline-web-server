package com.zylear.blokus.wsserver.bean.transfer;

/**
 * Created by xiezongyu on 2018/7/27.
 */
public class GameResultMsg {

    private Integer color;

    private Integer rank;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}
