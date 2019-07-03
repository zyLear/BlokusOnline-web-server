package com.zylear.blokus.wsserver.manager;


import com.zylear.blokus.wsserver.bean.gameinfo.PlayerRoomInfo;
import com.zylear.blokus.wsserver.bean.gameinfo.RoomInfo;
import com.zylear.blokus.wsserver.bean.transfer.ColorMsg;
import com.zylear.blokus.wsserver.bean.transfer.RoomInfoMsg;
import com.zylear.blokus.wsserver.bean.transfer.UserMsg;
import com.zylear.blokus.wsserver.bean.transfer.base.MessageBean;
import com.zylear.blokus.wsserver.bean.transfer.base.TransferBean;
import com.zylear.blokus.wsserver.cache.ServerCache;
import com.zylear.blokus.wsserver.config.DataSourceBlokusGameConfig;
import com.zylear.blokus.wsserver.constant.MsgType;
import com.zylear.blokus.wsserver.domain.GameAccount;
import com.zylear.blokus.wsserver.domain.GameLog;
import com.zylear.blokus.wsserver.domain.PlayerGameLog;
import com.zylear.blokus.wsserver.domain.PlayerGameRecord;
import com.zylear.blokus.wsserver.enums.*;
import com.zylear.blokus.wsserver.manager.basehandler.MessageHandler;
import com.zylear.blokus.wsserver.manager.callback.EmptyServerCacheCallback;
import com.zylear.blokus.wsserver.service.GameAccountService;
import com.zylear.blokus.wsserver.service.GameLogService;
import com.zylear.blokus.wsserver.service.PlayerGameLogService;
import com.zylear.blokus.wsserver.service.PlayerGameRecordService;
import com.zylear.blokus.wsserver.util.JsonUtil;
import com.zylear.blokus.wsserver.util.MessageFormatter;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by xiezongyu on 2018/7/18.
 */
@Component
public class MessageManager implements MessageHandler<TransferBean, List<TransferBean>> {

    private static final Logger logger = LoggerFactory.getLogger(MessageManager.class);

    private GameAccountService gameAccountService;
    private GameLogService gameLogService;
    private PlayerGameLogService playerGameLogService;
    private PlayerGameRecordService playerGameRecordService;
    private MessageManager self;

    private static final Integer WIN_CHANGE_SCORE_TEMPLATE = 23;
    private static final Integer LOSE_CHANGE_SCORE_TEMPLATE = -21;
    private static final Integer ESCAPE_CHANGE_SCORE_TEMPLATE = -25;
    private static final Integer SCORE_RANDOM_RANGE = 7; //excluded


    @Override
    public void handle(TransferBean transferBean, List<TransferBean> responses) {
        MessageBean messageBean = transferBean.getMessageBean();
        if (messageBean.getMsgType() != MsgType.PING && messageBean.getMsgType() != MsgType.ROOM_LIST)
            logger.info("handle msg :" + messageBean);
        switch (messageBean.getMsgType()) {
            case MsgType.LOGIN:
                login(transferBean, responses);
                break;
            case MsgType.CREATE_ROOM:
                createRoom(transferBean, responses);
                break;
            case MsgType.JOIN_ROOM:
                joinRoom(transferBean, responses);
                break;
            case MsgType.LEAVE_ROOM:
                leaveRoom(transferBean, responses);
                break;
            case MsgType.CHOOSE_COLOR:
                chooseColor(transferBean, responses);
                break;
            case MsgType.READY:
                ready(transferBean, responses);
                break;
            case MsgType.CHESS_DONE:
                chessDone(transferBean, responses);
                break;
            case MsgType.GIVE_UP:
                giveUp(transferBean, responses);
                break;
            case MsgType.LOSE:
                lose(transferBean, responses);
                break;
            case MsgType.WIN:
                win(transferBean, responses);
                break;
            case MsgType.LOGOUT:
                logout(transferBean, responses);
                break;
            case MsgType.ROOM_LIST:
                roomList(transferBean, responses);
                break;
            case MsgType.REGISTER:
                self.register(transferBean, responses);
                break;

            case MsgType.INIT_PLAYER_INFO_IN_GAME:
                initPlayerInfoInGame(transferBean, responses);
                break;


            case MsgType.QUIT:
                quit(transferBean, responses);
                break;
            default:
                break;
        }
    }


    private void initPlayerInfoInGame(TransferBean transferBean, List<TransferBean> responses) {
        RoomInfo roomInfo = ServerCache.getRoomInfo(transferBean.getChannel());
        if (roomInfo != null) {
            Map<String, PlayerRoomInfo> playerRoomInfoMap = roomInfo.getPlayers();
            MessageBean needSendMessage = MessageFormatter.formatPlayerInfoInGameMessage(playerRoomInfoMap);
            transferBean.setMessageBean(needSendMessage);
            responses.add(transferBean);
        }
    }

    private void roomList(TransferBean transferBean, List<TransferBean> responses) {
        Collection<RoomInfo> roomList = ServerCache.roomList();
        MessageBean message = MessageFormatter.formatRoomListMessage(roomList);

        responses.add(new TransferBean(transferBean.getChannel(), message));
    }

    private void notifyRoomList(TransferBean transferBean, List<TransferBean> responses) {
        Collection<RoomInfo> roomList = ServerCache.roomList();
        MessageBean message = MessageFormatter.formatRoomListMessage(roomList);
        Set<Channel> players = ServerCache.players();
        for (Channel channel : players) {
            responses.add(new TransferBean(channel, message));
        }
    }

    @Transactional(value = DataSourceBlokusGameConfig.TX_MANAGER)
    private synchronized void register(TransferBean transferBean, List<TransferBean> responses) {

        MessageBean message = transferBean.getMessageBean();
        UserMsg userMsg;
        try {
            userMsg = JsonUtil.getObjectFromJson(message.getContent(), UserMsg.class);
            logger.info("register. account:{}", userMsg.getAccount());
            logger.info("register. roomName:{}", userMsg.getPassword());
        } catch (Exception e) {
            logger.warn("parse UserMsg exception. ", e);
            responses.add(new TransferBean(transferBean.getChannel(), MessageBean.REGISTER_FAIL));
            return;
        }
        String account = userMsg.getAccount();
        String password = userMsg.getPassword();

        try {
            GameAccount gameAccount = gameAccountService.findByAccount(account);
            if (gameAccount == null) {
                Date date = new Date();
                gameAccount = new GameAccount();
                gameAccount.setAccount(account);
                gameAccount.setPassword(password);
                gameAccount.setStars(0);
                gameAccount.setCreateTime(date);
                gameAccount.setLastUpdateTime(date);
                gameAccountService.insert(gameAccount);

                PlayerGameRecord gameRecord = new PlayerGameRecord();
                gameRecord.setAccount(account);
                gameRecord.setGameType(GameType.blokus_four.getValue());
                gameRecord.setWinCount(0);
                gameRecord.setLoseCount(0);
                gameRecord.setEscapeCount(0);
                gameRecord.setRankScore(1200);
                gameRecord.setRank(0);
                gameRecord.setCreateTime(date);
                gameRecord.setLastUpdateTime(date);
                playerGameRecordService.insert(gameRecord);
                gameRecord.setGameType(GameType.blokus_two.getValue());
                playerGameRecordService.insert(gameRecord);

                responses.add(new TransferBean(transferBean.getChannel(), MessageBean.REGISTER_SUCCESS));
            }
        } catch (Exception e) {
            responses.add(new TransferBean(transferBean.getChannel(), MessageBean.REGISTER_FAIL));
            logger.info("register fail. ", e);
        }


    }

    private void logout(TransferBean transferBean, List<TransferBean> responses) {
        quit(transferBean, responses);
    }

    private void giveUp(TransferBean transferBean, List<TransferBean> responses) {
        MessageBean message = transferBean.getMessageBean();
        lose(transferBean, responses);

        List<Channel> players = ServerCache.getPlayerChannelsInRoom(transferBean.getChannel());
        for (Channel channel : players) {
            responses.add(new TransferBean(channel, message));
        }
    }


    private synchronized void win(TransferBean transferBean, List<TransferBean> responses) {
        PlayerRoomInfo playerRoomInfo = ServerCache.getPlayerRoomInfo(transferBean.getChannel());
        RoomInfo roomInfo = ServerCache.getRoomInfo(transferBean.getChannel());
        if (playerRoomInfo != null && roomInfo != null &&
                RoomStatus.gaming.equals(roomInfo.getRoomStatus()) &&
                !GameStatus.win.equals(playerRoomInfo.getGameStatus())) {
            logger.info("{} win", playerRoomInfo.getAccount());
            self.gameStatusChange(playerRoomInfo, roomInfo, GameResult.win);
        }
    }

    private synchronized void lose(TransferBean transferBean, List<TransferBean> responses) {
        PlayerRoomInfo playerRoomInfo = ServerCache.getPlayerRoomInfo(transferBean.getChannel());
        RoomInfo roomInfo = ServerCache.getRoomInfo(transferBean.getChannel());
        if (playerRoomInfo != null && roomInfo != null &&
                RoomStatus.gaming.equals(roomInfo.getRoomStatus()) &&
                GameStatus.gaming.equals(playerRoomInfo.getGameStatus())) {
            logger.info("{} lose", playerRoomInfo.getAccount());
            self.gameStatusChange(playerRoomInfo, roomInfo, GameResult.lose);

            PlayerRoomInfo winPlayer = roomInfo.canWin();
            if (winPlayer != null) {
                logger.info("{} win", winPlayer.getAccount());
                self.gameStatusChange(winPlayer, roomInfo, GameResult.win);
            }

            if (RoomStatus.waiting.equals(roomInfo.getRoomStatus())) {
                notifyRoomList(transferBean, responses);
            }
        }
    }


    @Transactional(value = DataSourceBlokusGameConfig.TX_MANAGER)
    private void gameStatusChange(PlayerRoomInfo playerRoomInfo, RoomInfo roomInfo, GameResult gameResult) {

        Integer changeScore;
        switch (gameResult) {
            case win:
                if (GameType.blokus_four.equals(roomInfo.getGameType())) {
                    changeScore = WIN_CHANGE_SCORE_TEMPLATE * 2 + RandomUtils.nextInt(0, SCORE_RANDOM_RANGE);
                } else {
                    changeScore = WIN_CHANGE_SCORE_TEMPLATE + RandomUtils.nextInt(0, SCORE_RANDOM_RANGE);
                }
                playerRoomInfo.setGameStatus(GameStatus.win);
                playerGameRecordService.update(playerRoomInfo.getAccount(), roomInfo.getGameType().getValue(), 1, 0, 0, changeScore);
                break;
            case lose:
                int currentLoseCount = roomInfo.getCurrentLoseCount();
                if (currentLoseCount == 0) {
                    changeScore = LOSE_CHANGE_SCORE_TEMPLATE - RandomUtils.nextInt(0, SCORE_RANDOM_RANGE);
                } else if (currentLoseCount == 1) {
                    changeScore = -RandomUtils.nextInt(0, SCORE_RANDOM_RANGE);
                } else {
                    changeScore = WIN_CHANGE_SCORE_TEMPLATE + RandomUtils.nextInt(0, SCORE_RANDOM_RANGE);
                }

                playerRoomInfo.setGameStatus(GameStatus.lose);
                roomInfo.setCurrentLoseCount(roomInfo.getCurrentLoseCount() + 1);
                playerGameRecordService.update(playerRoomInfo.getAccount(), roomInfo.getGameType().getValue(), 0, 1, 0, changeScore);
                break;
            case escape:
                changeScore = ESCAPE_CHANGE_SCORE_TEMPLATE - RandomUtils.nextInt(0, SCORE_RANDOM_RANGE);
                roomInfo.setCurrentLoseCount(roomInfo.getCurrentLoseCount() + 1);
                playerGameRecordService.update(playerRoomInfo.getAccount(), roomInfo.getGameType().getValue(), 0, 0, 1, changeScore);
                break;
            default:
                return;
        }
        roomInfo.checkRoomStatus();

        PlayerGameLog playerGameLog = new PlayerGameLog();
        playerGameLog.setAccount(playerRoomInfo.getAccount());
        playerGameLog.setGameLogId(roomInfo.getGameLogId());
        playerGameLog.setGameResult(gameResult.getValue());
        playerGameLog.setStepsCount(playerRoomInfo.getStepsCount());
        playerGameLog.setChangeScore(changeScore);
        playerGameLog.setCreateTime(new Date());
        playerGameLog.setLastUpdateTime(new Date());
        playerGameLogService.insert(playerGameLog);
    }


    private synchronized void quit(final TransferBean transferBean, final List<TransferBean> responses) {
        ServerCache.quit(transferBean.getChannel(), new EmptyServerCacheCallback() {
            @Override
            public void gameStatusChange(PlayerRoomInfo playerRoomInfo, RoomInfo roomInfo, GameResult gameResult) {
                self.gameStatusChange(playerRoomInfo, roomInfo, gameResult);
            }

            @Override
            public void updateRoomPlayersInfo(String roomName) {
                updateRoomPlayersInfoNotify(roomName, responses);
            }

            @Override
            public void giveUp(PlayerRoomInfo playerRoomInfo, RoomInfo roomInfo) {
                MessageBean messageBean = MessageFormatter.formatGiveUpMessage(playerRoomInfo.getColor());
                for (Entry<String, PlayerRoomInfo> entry : roomInfo.getPlayers().entrySet()) {
                    if (!playerRoomInfo.getAccount().equals(entry.getKey())) {
                        responses.add(new TransferBean(entry.getValue().getChannel(), messageBean));
                    }
                }
            }

            @Override
            public void notifyRoomList() {
                MessageManager.this.notifyRoomList(transferBean, responses);
            }
        });


    }


    private synchronized void login(TransferBean transferBean, List<TransferBean> responses) {
        MessageBean message = transferBean.getMessageBean();
        UserMsg userMsg;
        try {
            userMsg = JsonUtil.getObjectFromJson(message.getContent(), UserMsg.class);
            logger.info("login. account:{}", userMsg.getAccount());
            logger.info("login. password:{}", userMsg.getPassword());
        } catch (Exception e) {
            logger.warn("parse UserMsg exception. ", e);
            transferBean.setMessageBean(MessageBean.LOGIN_FAIL);
            responses.add(transferBean);
            return;

        }
        transferBean.setMessageBean(MessageBean.LOGIN_SUCCESS);
        GameAccount gameAccount =
                gameAccountService.findByAccountAndPassowrd(userMsg.getAccount(), userMsg.getPassword());
        if (gameAccount != null && ServerCache.login(transferBean.getChannel(), userMsg.getAccount())) {
            responses.add(transferBean);
        } else {
            transferBean.setMessageBean(MessageBean.LOGIN_FAIL);
            responses.add(transferBean);
            return;
        }

        if (!StringUtils.isEmpty(userMsg.getAccount())) {
            if (ServerCache.login(transferBean.getChannel(), userMsg.getAccount())) {
                responses.add(transferBean);
                return;
            }
        }

    }


    private synchronized void leaveRoom(TransferBean transferBean, List<TransferBean> responses) {
        String roomName = ServerCache.leaveRoom(transferBean.getChannel());
        if (roomName != null) {
            transferBean.setMessageBean(MessageBean.LEAVE_ROOM_SUCCESS);
            responses.add(transferBean);
            updateRoomPlayersInfoNotify(roomName, responses);
            notifyRoomList(transferBean, responses);
        } else {
            transferBean.setMessageBean(MessageBean.LEAVE_ROOM_FAIL);
            responses.add(transferBean);
        }
//        ServerCache.showAllRooms();

    }

    private synchronized void chooseColor(TransferBean transferBean, final List<TransferBean> responses) {

        MessageBean message = transferBean.getMessageBean();
        ColorMsg colorMsg;
        try {
            colorMsg = JsonUtil.getObjectFromJson(message.getContent(), ColorMsg.class);
            logger.info("choose color. color:{}", BlokusColor.valueOf(colorMsg.getColor()));
        } catch (Exception e) {
            logger.warn("parse ColorMsg exception. ", e);
            return;
        }

        ServerCache.chooseColor(transferBean.getChannel(), BlokusColor.valueOf(colorMsg.getColor()), new EmptyServerCacheCallback() {
            @Override
            public void updateRoomPlayersInfo(String roomName) {
                updateRoomPlayersInfoNotify(roomName, responses);
            }
        });

    }

    private synchronized void ready(final TransferBean transferBean, final List<TransferBean> responses) {
        ServerCache.ready(transferBean.getChannel(), new EmptyServerCacheCallback() {
            @Override
            public void startGame(RoomInfo roomInfo) {
                if (roomInfo != null) {
                    Date date = new Date();
                    GameLog gameLog = new GameLog();
                    gameLog.setRoomName(roomInfo.getRoomName());
                    gameLog.setGameType(roomInfo.getGameType().getValue());
                    gameLog.setCreateTime(date);
                    gameLog.setLastUpdateTime(date);

                    gameLogService.insert(gameLog);
                    roomInfo.setGameLogId(gameLog.getId());
                    startGameNotify(roomInfo.getRoomName(), responses);
                    MessageManager.this.notifyRoomList(transferBean, responses);
                }
            }

            @Override
            public void updateRoomPlayersInfo(String roomName) {
                updateRoomPlayersInfoNotify(roomName, responses);
            }
        });
    }

    private void startGameNotify(String roomName, List<TransferBean> responses) {
        RoomInfo roomInfo = ServerCache.getRoomInfo(roomName);
        Map<String, PlayerRoomInfo> playerRoomInfoMap = roomInfo.getPlayers();
        for (Entry<String, PlayerRoomInfo> entry : playerRoomInfoMap.entrySet()) {
            PlayerRoomInfo value = entry.getValue();
            MessageBean message = MessageFormatter.formatStartMsg(MsgType.START_BLOKUS, value.getColor(), roomInfo.getGameType());
            responses.add(new TransferBean(value.getChannel(), message));
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
        RoomInfoMsg roomInfoMsg;
        try {
            roomInfoMsg = JsonUtil.getObjectFromJson(message.getContent(), RoomInfoMsg.class);
            logger.info("create room. roomName:{}", roomInfoMsg.getRoomName());
            logger.info("create room. gameType:{}", GameType.valueOf(roomInfoMsg.getGameType()));
        } catch (Exception e) {
            logger.warn("parse BLOKUSCreateRoom exception. ", e);
            transferBean.setMessageBean(MessageBean.CREATE_ROOM_FAIL);
            responses.add(transferBean);
            return;
        }

        if (ServerCache.createRoom(transferBean.getChannel(), roomInfoMsg.getRoomName(),
                GameType.valueOf(roomInfoMsg.getGameType()))) {
            MessageBean messageBean = MessageFormatter.formatRoomInfoWithSuccessRespnoseMsg(MsgType.CREATE_ROOM_RESPONSE, roomInfoMsg);
            transferBean.setMessageBean(messageBean);
            responses.add(transferBean);
            updateRoomPlayersInfoNotify(roomInfoMsg.getRoomName(), responses);
            notifyRoomList(transferBean, responses);
        } else {
            transferBean.setMessageBean(MessageBean.CREATE_ROOM_FAIL);
            responses.add(transferBean);
        }
//        ServerCache.showAllRooms();

    }

    private synchronized void joinRoom(TransferBean transferBean, List<TransferBean> responses) {
        MessageBean message = transferBean.getMessageBean();
        RoomInfoMsg roomInfoMsg;
        try {
            roomInfoMsg = JsonUtil.getObjectFromJson(message.getContent(), RoomInfoMsg.class);
            logger.info("join room. roomName:{}", roomInfoMsg.getRoomName());
        } catch (Exception e) {
            logger.warn("parse RoomInfoMsg exception. ", e);
            transferBean.setMessageBean(MessageBean.JOIN_ROOM_FAIL);
            responses.add(transferBean);
            return;
        }

        if (ServerCache.joinRoom(transferBean.getChannel(), roomInfoMsg.getRoomName())) {

            RoomInfo roomInfo = ServerCache.getRoomInfo(roomInfoMsg.getRoomName());
            if (roomInfo != null) {
                MessageBean messageBean = MessageFormatter.formatRoomInfoWithSuccessRespnoseMsg(MsgType.JOIN_ROOM_RESPONSE, roomInfoMsg);
                responses.add(new TransferBean(transferBean.getChannel(), messageBean));
                updateRoomPlayersInfoNotify(roomInfoMsg.getRoomName(), responses);
                notifyRoomList(transferBean, responses);
            } else {
                transferBean.setMessageBean(MessageBean.JOIN_ROOM_FAIL);
                responses.add(transferBean);
            }
        }
    }


    private void updateRoomPlayersInfoNotify(String roomName, List<TransferBean> responses) {
        Map<String, PlayerRoomInfo> playerRoomInfoMap = ServerCache.getPlayerRoomInfos(roomName);
        MessageBean needSendMessage = MessageFormatter.formatRoomPlayersInfoMessage(playerRoomInfoMap);
        for (Entry<String, PlayerRoomInfo> entry : playerRoomInfoMap.entrySet()) {
            responses.add(new TransferBean(entry.getValue().getChannel(), needSendMessage));
        }
    }


    @Override
    public void send(List<TransferBean> transferBeans) {
        if (transferBeans != null) {
            for (TransferBean transferBean : transferBeans) {
                transferBean.getChannel().writeAndFlush(new TextWebSocketFrame(JsonUtil.toJSONString(transferBean.getMessageBean())));
            }
        }
    }

    @Autowired
    public void setGameAccountService(GameAccountService gameAccountService) {
        this.gameAccountService = gameAccountService;
    }

    @Autowired
    public void setGameLogService(GameLogService gameLogService) {
        this.gameLogService = gameLogService;
    }

    @Autowired
    public void setPlayerGameLogService(PlayerGameLogService playerGameLogService) {
        this.playerGameLogService = playerGameLogService;
    }

    @Autowired
    public void setPlayerGameRecordService(PlayerGameRecordService playerGameRecordService) {
        this.playerGameRecordService = playerGameRecordService;
    }

    @Autowired
    public void setSelf(MessageManager self) {
        this.self = self;
    }
}
