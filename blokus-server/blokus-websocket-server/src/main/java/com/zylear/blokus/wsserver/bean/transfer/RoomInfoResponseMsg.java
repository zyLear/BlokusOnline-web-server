package com.zylear.blokus.wsserver.bean.transfer;

import com.zylear.blokus.wsserver.bean.transfer.base.ResponseMsg;

/**
 * Created by xiezongyu on 2018/7/27.
 */
public class RoomInfoResponseMsg extends ResponseMsg {


    public RoomInfoResponseMsg(String roomName, Integer gameType) {
        this.roomName = roomName;
        this.gameType = gameType;
    }

    public RoomInfoResponseMsg(Integer errorCode, String errorMsg, String roomName, Integer gameType) {
        super(errorCode, errorMsg);
        this.roomName = roomName;
        this.gameType = gameType;
    }

    public RoomInfoResponseMsg(ResponseMsg responseMsg, String roomName, Integer gameType) {
        super(responseMsg);
        this.roomName = roomName;
        this.gameType = gameType;
    }

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
