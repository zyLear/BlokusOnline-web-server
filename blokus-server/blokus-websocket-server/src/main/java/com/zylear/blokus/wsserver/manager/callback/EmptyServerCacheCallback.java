package com.zylear.blokus.wsserver.manager.callback;


import com.zylear.blokus.wsserver.bean.gameinfo.PlayerRoomInfo;
import com.zylear.blokus.wsserver.bean.gameinfo.RoomInfo;
import com.zylear.blokus.wsserver.enums.GameResult;

/**
 * Created by xiezongyu on 2018/3/10.
 */
public class EmptyServerCacheCallback implements ServerCacheCallback {


    @Override
    public void startGame(RoomInfo roomInfo) {

    }

    @Override
    public void updateRoomPlayersInfo(String roomName) {

    }

    @Override
    public void gameStatusChange(PlayerRoomInfo playerRoomInfo, RoomInfo roomInfo, GameResult gameResult) {

    }

    @Override
    public void giveUp(PlayerRoomInfo playerRoomInfo, RoomInfo roomInfo) {

    }

    @Override
    public void notifyRoomList() {

    }


}
