package com.zylear.blokus.wsserver.cache;

import com.zylear.blokus.wsserver.bean.gameinfo.PlayerInfo;
import com.zylear.blokus.wsserver.bean.gameinfo.PlayerRoomInfo;
import com.zylear.blokus.wsserver.bean.gameinfo.RoomInfo;
import com.zylear.blokus.wsserver.enums.*;
import com.zylear.blokus.wsserver.manager.callback.EmptyServerCacheCallback;
import com.zylear.blokus.wsserver.manager.callback.ServerCacheCallback;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiezongyu on 2018/7/18.
 */
public class ServerCache {

    //    public static final ChannelGroup connectChannelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
//    public static final Map<String, PlayerInfo> loginChannel = new ConcurrentHashMap<>();
//    public static final Map<String, ChannelGroup> roomChannelGroupMap = new ConcurrentHashMap<>();
//    public static final Map<String, String> playerInfo = new ConcurrentHashMap<>();

    private static final Map<Channel, PlayerInfo> playerMap = new ConcurrentHashMap<>();
    private static final Map<String, RoomInfo> roomMap = new ConcurrentHashMap<>();
//    private static final Set<String> playerAccountSet = new ConcurrentSet<>();


//    public static void addPlayer(String account, Channel channel) {
//        PlayerInfo player = new PlayerInfo();
//        player.setAccount(account);
//        player.setChannel(channel);
//        playerMap.put(channel, player);
//    }

    //preliminary lock  , improve later
    public synchronized static boolean createRoom(Channel channel, String roomName, GameType gameType) {

        if (!roomMap.containsKey(roomName)) {
            PlayerInfo playerInfo = playerMap.get(channel);
            if (playerInfo != null) {
                RoomInfo roomInfo = new RoomInfo();
                roomInfo.setPlayerCount(1);

                roomInfo.setRoomName(roomName);
                roomInfo.setGameType(gameType);
                if (GameType.blokus_two.equals(gameType)) {
                    roomInfo.setMaxPlayerCount(2);
                } else if (GameType.blokus_four.equals(gameType)) {
                    roomInfo.setMaxPlayerCount(4);
                } else {
                    return false;
                }
                roomInfo.setRoomStatus(RoomStatus.waiting);

                PlayerRoomInfo playerRoomInfo = new PlayerRoomInfo();
                playerRoomInfo.setAccount(playerInfo.getAccount());
                playerRoomInfo.setReady(false);
                playerRoomInfo.setColor(BlokusColor.blue);
                playerRoomInfo.setChannel(channel);
                roomInfo.getPlayers().put(playerInfo.getAccount(), playerRoomInfo);

                playerInfo.setRoomName(roomName);
                playerInfo.setRoomInfo(roomInfo);
                roomMap.put(roomName, roomInfo);

                return true;
            }
        }
        return false;
    }

    public static boolean login(Channel channel, String account) {

        if (StringUtils.isEmpty(account)) {
            return false;
        }

        if (!isContainsAccount(account)) {
            PlayerInfo player = new PlayerInfo();
            player.setAccount(account);
            player.setChannel(channel);
//            playerAccountSet.add(account);
            playerMap.put(channel, player);
            return true;
        }
        return false;
    }

    public static void quit(Channel channel, ServerCacheCallback serverCacheCallback) {
        PlayerInfo playerInfo = playerMap.get(channel);
        if (playerInfo != null) {
            RoomInfo roomInfo = playerInfo.getRoomInfo();
            if (roomInfo != null) {
                PlayerRoomInfo playerRoomInfo = roomInfo.getPlayers().get(playerInfo.getAccount());
                int playerCount = roomInfo.getPlayerCount();
                if (playerCount <= 1) {
                    if (RoomStatus.gaming.equals(roomInfo.getRoomStatus()) &&
                            GameStatus.gaming.equals(playerRoomInfo.getGameStatus())) {
                        serverCacheCallback.gameStatusChange(playerRoomInfo, roomInfo, GameResult.win);
                    }
                    roomMap.remove(roomInfo.getRoomName());
                } else {
                    roomInfo.setPlayerCount(playerCount - 1);
                    roomInfo.getPlayers().remove(playerInfo.getAccount());
                    if (RoomStatus.gaming.equals(roomInfo.getRoomStatus()) &&
                            GameStatus.gaming.equals(playerRoomInfo.getGameStatus())) {
                        serverCacheCallback.giveUp(playerRoomInfo, roomInfo);
                    } else if (RoomStatus.waiting.equals(roomInfo.getRoomStatus())) {
                        serverCacheCallback.updateRoomPlayersInfo(roomInfo.getRoomName());
                    }
                }
                serverCacheCallback.notifyRoomList();
            }
            playerMap.remove(channel);
        }
    }

    public static PlayerInfo getPlayerInfo(Channel channel) {
        return playerMap.get(channel);
    }

    public static boolean joinRoom(Channel channel, String roomName) {

        PlayerInfo player = playerMap.get(channel);
        if (player != null) {
            RoomInfo roomInfo = roomMap.get(roomName);
            if (player.getRoomInfo() == null && roomInfo != null &&
                    RoomStatus.waiting.equals(roomInfo.getRoomStatus()) &&
                    roomInfo.getPlayerCount() < roomInfo.getMaxPlayerCount()) {

                roomInfo.setPlayerCount(roomInfo.getPlayerCount() + 1);
                PlayerRoomInfo playerRoomInfo = new PlayerRoomInfo();
                playerRoomInfo.setAccount(player.getAccount());
                playerRoomInfo.setReady(false);
                playerRoomInfo.setColor(BlokusColor.blue);
                playerRoomInfo.setChannel(channel);

                roomInfo.getPlayers().put(player.getAccount(), playerRoomInfo);
                player.setRoomInfo(roomInfo);
                player.setRoomName(roomName);
                return true;
            }
        }
        return false;
    }

    public static String leaveRoom(Channel channel) {
        PlayerInfo player = playerMap.get(channel);
        if (player != null) {
            RoomInfo roomInfo = player.getRoomInfo();

            if (roomInfo != null && /*RoomStatus.waiting.equals(roomInfo.getRoomStatus()) &&*/
                    roomInfo.getPlayerCount() > 0) {
                player.setRoomInfo(null);
                if (roomInfo.getPlayerCount() == 1) {
                    roomMap.remove(roomInfo.getRoomName());
                } else {
                    roomInfo.setPlayerCount(roomInfo.getPlayerCount() - 1);
                    roomInfo.getPlayers().remove(player.getAccount());
                }
                return roomInfo.getRoomName();
            }
        }
        return null;
    }


    public static void ready(Channel channel, ServerCacheCallback serverCacheCallback) {
        PlayerInfo playerInfo = playerMap.get(channel);
        if (playerInfo != null) {
            RoomInfo roomInfo = playerInfo.getRoomInfo();
            if (roomInfo != null) {
                PlayerRoomInfo playerRoomInfo = roomInfo.getPlayers().get(playerInfo.getAccount());
                if (playerRoomInfo != null) {
                    boolean ready = playerRoomInfo.getReady();
                    if (!ready) {
//                        serverCacheCallback.startGame(roomInfo);
//                        return;
                        if (roomInfo.canStartGame()) {
                            //重置本局相关信息
                            roomInfo.restart();
                            for (Entry<String, PlayerRoomInfo> entry : roomInfo.getPlayers().entrySet()) {
                                entry.getValue().setReady(false);
                                entry.getValue().setStepsCount(0);
                                entry.getValue().setGameStatus(GameStatus.gaming);
                            }
                            serverCacheCallback.startGame(roomInfo);
                            roomInfo.setGameLogId(roomInfo.getGameLogId());
                        } else if (roomInfo.canReady()) {
                            roomInfo.getPlayers().get(playerInfo.getAccount()).setReady(true);
                            serverCacheCallback.updateRoomPlayersInfo(roomInfo.getRoomName());
                        }
                    } else {
                        playerRoomInfo.setReady(false);
                        serverCacheCallback.updateRoomPlayersInfo(roomInfo.getRoomName());
                    }
                }
            }
        }
    }


    public static Map<String, PlayerRoomInfo> getPlayerRoomInfos(String roomName) {

        RoomInfo roomInfo = roomMap.get(roomName);
        if (roomInfo != null) {
            return roomInfo.getPlayers();
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    public static void removePlayer(Channel channel) {
        playerMap.remove(channel);
    }

    public static void removeRoom(String roomName) {
        roomMap.remove(roomName);
    }

    public static RoomInfo getRoomInfo(String roomName) {
        return roomMap.get(roomName);

    }

    public static void chooseColor(Channel channel, BlokusColor color, EmptyServerCacheCallback serverCacheCallback) {
        PlayerInfo playerInfo = playerMap.get(channel);
        if (playerInfo != null) {
            RoomInfo roomInfo = playerInfo.getRoomInfo();
            if (roomInfo != null) {
                for (Entry<String, PlayerRoomInfo> entry : roomInfo.getPlayers().entrySet()) {
                    if (entry.getKey().equals(playerInfo.getAccount())) {
                        entry.getValue().setColor(color);
                    }
                    entry.getValue().setReady(false);
                }
                serverCacheCallback.updateRoomPlayersInfo(roomInfo.getRoomName());
            }
        }

    }

    public static Collection<RoomInfo> roomList() {
        return roomMap.values();
    }

    public static Set<Channel> players() {
        return playerMap.keySet();
    }


//    public static List<Channel> getPlayersChannelInRoom(String roomName) {
//        try {
//            RoomInfo roomInfo = roomMap.get(roomName);
//            Set<String> accounts = roomInfo.getPlayers().keySet();
//            List<Channel> list = new ArrayList<>(roomInfo.getPlayerCount());
//            for (Entry<Channel, PlayerInfo> entry : playerMap.entrySet()) {
//                if (accounts.contains(entry.getValue().getAccount())) {
//                    list.add(entry.getValue().getChannel());
//                }
//            }
//            return list;
//        } catch (Exception e) {
//            return Collections.EMPTY_LIST;
//        }
//    }


    private static boolean isContainsAccount(String acocunt) {
        for (Entry<Channel, PlayerInfo> entry : playerMap.entrySet()) {
            if (acocunt.equals(entry.getValue().getAccount())) {
                return true;
            }
        }
        return false;
    }

    public static void showAllRooms() {
        for (Entry<String, RoomInfo> entry : roomMap.entrySet()) {
            entry.getValue().toString();
        }
    }


    public static List<Channel> getPlayerChannelsInRoom(Channel channel) {

        PlayerInfo player = playerMap.get(channel);

        List<Channel> channels = new ArrayList<>(4);
        if (player != null) {
            RoomInfo roomInfo = player.getRoomInfo();
            if (roomInfo != null) {
                for (Entry<String, PlayerRoomInfo> entry : roomInfo.getPlayers().entrySet()) {
                    channels.add(entry.getValue().getChannel());
                }
                return channels;
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static List<Channel> getOtherPlayerChannelsInRoom(Channel channel) {

        PlayerInfo player = playerMap.get(channel);

        List<Channel> channels = new ArrayList<>(4);
        if (player != null) {
            String account = player.getAccount();
            RoomInfo roomInfo = player.getRoomInfo();
            if (roomInfo != null) {
                for (Entry<String, PlayerRoomInfo> entry : roomInfo.getPlayers().entrySet()) {

                    if (!account.equals(entry.getValue().getAccount())) {
                        channels.add(entry.getValue().getChannel());
                    }
                }
                return channels;
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static PlayerRoomInfo getPlayerRoomInfo(Channel channel) {
        PlayerInfo player = playerMap.get(channel);
        if (player != null) {
            RoomInfo roomInfo = player.getRoomInfo();
            if (roomInfo != null) {
                return roomInfo.getPlayers().get(player.getAccount());
            }
        }
        return null;
    }


    public static RoomInfo getRoomInfo(Channel channel) {
        PlayerInfo player = playerMap.get(channel);
        if (player != null) {
            return player.getRoomInfo();
        }
        return null;
    }


}


