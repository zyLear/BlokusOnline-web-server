package com.zylear.blokus.wsserver.util;


import com.zylear.blokus.wsserver.bean.gameinfo.PlayerRoomInfo;
import com.zylear.blokus.wsserver.bean.gameinfo.RoomInfo;
import com.zylear.blokus.wsserver.bean.transfer.*;
import com.zylear.blokus.wsserver.bean.transfer.RoomListMsg.RoomItemMsg;
import com.zylear.blokus.wsserver.bean.transfer.RoomPlayerListMsg.RoomPlayerInfoMsg;
import com.zylear.blokus.wsserver.bean.transfer.base.MessageBean;
import com.zylear.blokus.wsserver.bean.transfer.base.ResponseMsg;
import com.zylear.blokus.wsserver.constant.MsgType;
import com.zylear.blokus.wsserver.enums.BlokusColor;
import com.zylear.blokus.wsserver.enums.GameType;
import com.zylear.blokus.wsserver.enums.RoomStatus;
import com.zylear.blokus.wsserver.bean.transfer.StartBlokusMsg.PlayerInfoMsg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by xiezongyu on 2018/7/17.
 */
public class MessageFormatter {
    public static MessageBean formatRoomInfoWithSuccessRespnoseMsg(int msgType, RoomInfoMsg roomInfoMsg) {
        MessageBean messageBean = new MessageBean();
        messageBean.setMsgType(msgType);
        RoomInfoResponseMsg roomInfoResponseMsg = new RoomInfoResponseMsg(ResponseMsg.SUCCESS, roomInfoMsg.getRoomName(), roomInfoMsg.getGameType());
        messageBean.setContent(JsonUtil.toJSONString(roomInfoResponseMsg));
        return messageBean;
    }


    public static MessageBean formatRoomPlayersInfoMessage(Map<String, PlayerRoomInfo> playerRoomInfoMap) {
        MessageBean message = new MessageBean();
        message.setMsgType(MsgType.UPDATE_ROOM_PLAYERS_INFO);
        List<RoomPlayerInfoMsg> list = new ArrayList<>(playerRoomInfoMap.size());
        for (Entry<String, PlayerRoomInfo> entry : playerRoomInfoMap.entrySet()) {
            PlayerRoomInfo playerRoomInfo = entry.getValue();
            RoomPlayerInfoMsg roomPlayerInfoMsg = new RoomPlayerInfoMsg();
            roomPlayerInfoMsg.setAccount(playerRoomInfo.getAccount());
            roomPlayerInfoMsg.setColor(playerRoomInfo.getColor().getValue());
            roomPlayerInfoMsg.setIsReady(playerRoomInfo.getReady());
            list.add(roomPlayerInfoMsg);
        }
        RoomPlayerListMsg roomPlayerListMsg = new RoomPlayerListMsg();
        roomPlayerListMsg.setPlayerInfoMsgList(list);
        message.setContent(JsonUtil.toJSONString(roomPlayerListMsg));
        return message;
    }

    public static MessageBean formatGiveUpMessage(BlokusColor color) {
        MessageBean message = new MessageBean();
        message.setMsgType(MsgType.GIVE_UP);
        ColorMsg colorMsg = new ColorMsg();
        colorMsg.setColor(color.getValue());
        message.setContent(JsonUtil.toJSONString(colorMsg));
        return message;
    }

    public static MessageBean formatColorMessage(Integer msgType, BlokusColor color) {
        MessageBean message = new MessageBean();
        message.setMsgType(msgType);
        ColorMsg colorMsg = new ColorMsg();
        colorMsg.setColor(color.getValue());
        message.setContent(JsonUtil.toJSONString(colorMsg));
        return message;
    }


    public static MessageBean formatRoomListMessage(Collection<RoomInfo> roomList) {
        MessageBean message = new MessageBean();
        message.setMsgType(MsgType.ROOM_LIST);
        List<RoomItemMsg> list = new ArrayList<>(roomList.size());
        RoomListMsg roomListMsg = new RoomListMsg();
        for (RoomInfo roomInfo : roomList) {
            RoomItemMsg roomItem = new RoomItemMsg();
            roomItem.setRoomName(roomInfo.getRoomName());
            if (GameType.blokus_four.equals(roomInfo.getGameType())) {
                roomItem.setMaxPlayerCount(4);
            } else {
                roomItem.setMaxPlayerCount(2);
            }
            roomItem.setRoomStatus(getRoomStatusText(roomInfo.getRoomStatus()));
            roomItem.setCurrentPlayerCount(roomInfo.getPlayerCount());
            list.add(roomItem);
        }
        roomListMsg.setRoomItems(list);
        message.setContent(JsonUtil.toJSONString(roomListMsg));
        return message;

    }

    private static String getRoomStatusText(RoomStatus roomStatus) {
        switch (roomStatus) {
            case gaming:
                return "游戏中";
            case waiting:
                return "等待中";
            default:
                return "未知";
        }
    }

    public static MessageBean formatPlayerInfoInGameMessage(Map<String, PlayerRoomInfo> playerRoomInfoMap) {
        MessageBean message = new MessageBean();
        message.setMsgType(MsgType.INIT_PLAYER_INFO_IN_GAME);
        List<RoomPlayerInfoMsg> list = new ArrayList<>(playerRoomInfoMap.size());
        RoomPlayerListMsg roomPlayerListMsg = new RoomPlayerListMsg();
        for (Entry<String, PlayerRoomInfo> entry : playerRoomInfoMap.entrySet()) {
            PlayerRoomInfo playerRoomInfo = entry.getValue();
            RoomPlayerInfoMsg item = new RoomPlayerInfoMsg();
            item.setAccount(playerRoomInfo.getAccount());
            item.setColor(playerRoomInfo.getColor().getValue());
            list.add(item);
        }
        roomPlayerListMsg.setPlayerInfoMsgList(list);
        message.setContent(JsonUtil.toJSONString(roomPlayerListMsg));
        return message;
    }

    public static MessageBean formatStartMsg(int msgType, PlayerRoomInfo player,
                                             Collection<PlayerRoomInfo> playerRoomInfos, GameType gameType) {
        MessageBean message = new MessageBean();
        message.setMsgType(msgType);
        StartBlokusMsg startBlokusMsg = new StartBlokusMsg();
        startBlokusMsg.setColor(player.getColor().getValue());
        startBlokusMsg.setAccount(player.getAccount());
        startBlokusMsg.setGameType(gameType.getValue());
        List<PlayerInfoMsg> list = new ArrayList<>(playerRoomInfos.size());
        for (PlayerRoomInfo playerRoomInfo : playerRoomInfos) {
            PlayerInfoMsg playerInfoMsg = new PlayerInfoMsg();
            playerInfoMsg.setAccount(playerRoomInfo.getAccount());
            playerInfoMsg.setColor(playerRoomInfo.getColor().getValue());
            list.add(playerInfoMsg);
        }
        startBlokusMsg.setPlayerList(list);
        message.setContent(JsonUtil.toJSONString(startBlokusMsg));
        return message;
    }


    private static String getColorText(BlokusColor color) {
        switch (color) {
            case red:
                return "红色";
            case blue:
                return "蓝色";
            case green:
                return "绿色";
            case yellow:
                return "黄色";
            default:
                return "未知";
        }

    }


}
