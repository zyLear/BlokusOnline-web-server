package com.zylear.blokus.wsserver.constant;

/**
 * Created by xiezongyu on 2018/7/17.
 */
public class MsgType {

    public static final int CHECK_VERSION = 1;

    public static final int LOGIN = 2;

    public static final int CREATE_ROOM = 3;

    public static final int JOIN_ROOM = 4;

    public static final int LEAVE_ROOM = 5;

    public static final int READY = 6;

    public static final int CHESS_DONE = 7;

    public static final int UPDATE_ROOM_PLAYERS_INFO = 8;

    public static final int CHOOSE_COLOR = 9;

    public static final int START_BLOKUS = 10;

    public static final int START_BLOKUS_TWO_PEOPLE = 11;

    public static final int WIN = 12;

    public static final int LOSE = 13;

    public static final int GIVE_UP = 14;

    public static final int CHAT_IN_GAME = 15;

    public static final int ROOM_LIST = 16;

    public static final int REGISTER = 17;

    public static final int RANK_INFO = 18;

    public static final int PROFILE = 19;

    public static final int CHAT_IN_ROOM = 20;

    public static final int LEAVE_ROOM_FROM_GAME = 21;

    public static final int INIT_PLAYER_INFO_IN_GAME = 22;

    public static final int LOGOUT = 23;

    public static final int CREATE_ROOM_RESPONSE = 24;

    public static final int LOGIN_RESPONSE = 25;

    public static final Integer JOIN_ROOM_RESPONSE = 26;

    public static final Integer LEAVE_ROOM_RESPONSE = 27;


    public static final int PING = 10000;

    public static final int QUIT = 10001;


}
