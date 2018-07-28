package com.zylear.blokus.wsserver.service;


import com.zylear.blokus.wsserver.domain.GameLog;

/**
 * Created by xiezongyu on 2018/3/10.
 */
public interface GameLogService {

    void insert(GameLog gameLog);

    GameLog findById(Integer gameLogId);
}
