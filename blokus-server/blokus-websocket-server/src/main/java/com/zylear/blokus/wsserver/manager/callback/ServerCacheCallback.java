package com.zylear.blokus.wsserver.manager.callback;


import com.zylear.blokus.wsserver.bean.gameinfo.PlayerRoomInfo;
import com.zylear.blokus.wsserver.bean.gameinfo.RoomInfo;
import com.zylear.blokus.wsserver.enums.GameResult;

/**
 * Created by xiezongyu on 2018/3/10.
 */
public interface ServerCacheCallback {

    void startGame(RoomInfo roomInfo);

    void updateRoomPlayersInfo(String roomName);

    void gameStatusChange(PlayerRoomInfo playerRoomInfo, RoomInfo roomInfo, GameResult gameResult);

    void giveUp(PlayerRoomInfo playerRoomInfo, RoomInfo roomInfo);

    void notifyRoomList();
}
