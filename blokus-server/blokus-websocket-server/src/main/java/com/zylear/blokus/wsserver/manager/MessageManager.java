package com.zylear.blokus.wsserver.manager;


import com.zylear.blokus.wsserver.bean.gameinfo.PlayerRoomInfo;
import com.zylear.blokus.wsserver.bean.transfer.RoomInfoMsg;
import com.zylear.blokus.wsserver.bean.transfer.base.MessageBean;
import com.zylear.blokus.wsserver.bean.transfer.base.TransferBean;
import com.zylear.blokus.wsserver.cache.ServerCache;
import com.zylear.blokus.wsserver.constant.MsgType;
import com.zylear.blokus.wsserver.enums.GameType;
import com.zylear.blokus.wsserver.manager.basehandler.MessageHandler;
import com.zylear.blokus.wsserver.util.JsonUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/7/18.
 */
@Component
public class MessageManager implements MessageHandler<TransferBean, List<TransferBean>> {

    private static final Logger logger = LoggerFactory.getLogger(MessageManager.class);


    @Override
    public void handle(TransferBean transferBean, List<TransferBean> responses) {
        MessageBean messageBean = transferBean.getMessageBean();
        logger.info("handle msg :" + messageBean);
        switch (messageBean.getMsgType()) {
            case MsgType.LOGIN:

                break;
            case MsgType.CHESS_DONE:
                chessDone(transferBean, responses);
                break;

            default:
                break;
        }
    }

    private synchronized void chessDone(TransferBean transferBean, List<TransferBean> responses) {

        PlayerRoomInfo playerRoomInfo = ServerCache.getPlayerRoomInfo(transferBean.getChannel());
        if (playerRoomInfo != null) {
            playerRoomInfo.setStepsCount(playerRoomInfo.getStepsCount() + 1);
            List<Channel> channels = ServerCache.getOtherPlayerChannelsInRoom(transferBean.getChannel());
            for (Channel channel : channels) {
                responses.add(new TransferBean(channel, transferBean.getMessageBean()));
            }
        }


//        return null;
    }

    private synchronized void createRoom(TransferBean transferBean, List<TransferBean> responses) {
        MessageBean message = transferBean.getMessageBean();
        RoomInfoMsg roomInfoMsg = null;
        try {
            roomInfoMsg = JsonUtil.getObjectFromJson(message.getContent(), RoomInfoMsg.class);
            logger.info("create room. roomName:{}", roomInfoMsg.getRoomName());
            logger.info("create room. gameType:{}", GameType.valueOf(roomInfoMsg.getGameType()));
        } catch (Exception e) {
            logger.warn("parse BLOKUSCreateRoom exception. ", e);
//            transferBean.setMessage(MessageBean.CREATE_ROOM_FAIL);
            responses.add(transferBean);
            return;
        }

        if (ServerCache.createRoom(transferBean.getChannel(), roomInfoMsg.getRoomName(),
                GameType.valueOf(roomInfoMsg.getGameType()))) {

            transferBean.setMessageBean(transferBean.getMessageBean());
//            responses.add(transferBean);
//            updateRoomPlayersInfoNotify(blokusCreateRoom.getRoomName(), responses);
        } else {
//            transferBean.setMessageBean(MessageBean.CREATE_ROOM_FAIL);
//            responses.add(transferBean);
        }
//        ServerCache.showAllRooms();

    }


    @Override
    public void send(List<TransferBean> transferBeans) {
        if (transferBeans != null) {
            for (TransferBean transferBean : transferBeans) {
                transferBean.getChannel().writeAndFlush(new TextWebSocketFrame(JsonUtil.toJSONString(transferBean.getMessageBean())));
            }
        }
    }


}
