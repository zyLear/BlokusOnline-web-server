package com.zylear.blokus.wsserver.manager.callback;


import com.zylear.blokus.wsserver.bean.gameinfo.RoomInfo;

/**
 * Created by xiezongyu on 2018/3/10.
 */
public interface ServerCacheCallback {

    void startGame(RoomInfo roomInfo);

    void updateRoomPlayersInfo(String roomName);

}
