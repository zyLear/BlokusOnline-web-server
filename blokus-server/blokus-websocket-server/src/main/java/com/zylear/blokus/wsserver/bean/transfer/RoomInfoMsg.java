package com.zylear.blokus.wsserver.bean.transfer;

/**
 * Created by xiezongyu on 2018/7/27.
 */
public class RoomInfoMsg {
    private String roomName;
    private Integer gameType;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getGameType() {
        return gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }
}
