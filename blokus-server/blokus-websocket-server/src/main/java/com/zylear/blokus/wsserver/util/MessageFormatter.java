package com.zylear.blokus.wsserver.util;


import com.zylear.blokus.wsserver.bean.gameinfo.PlayerRoomInfo;
import com.zylear.blokus.wsserver.bean.transfer.ColorMsg;
import com.zylear.blokus.wsserver.bean.transfer.RoomInfoResponseMsg;
import com.zylear.blokus.wsserver.bean.transfer.RoomInfoMsg;
import com.zylear.blokus.wsserver.bean.transfer.RoomPlayerListMsg;
import com.zylear.blokus.wsserver.bean.transfer.RoomPlayerListMsg.RoomPlayerInfoMsg;
import com.zylear.blokus.wsserver.bean.transfer.base.MessageBean;
import com.zylear.blokus.wsserver.bean.transfer.base.ResponseMsg;
import com.zylear.blokus.wsserver.constant.MsgType;
import com.zylear.blokus.wsserver.enums.BlokusColor;

import java.util.ArrayList;
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
}
