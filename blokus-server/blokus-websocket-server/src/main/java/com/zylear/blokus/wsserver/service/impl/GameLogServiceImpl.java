package com.zylear.blokus.wsserver.service.impl;

import com.zylear.blokus.wsserver.blokusgame.GameLogMapper;
import com.zylear.blokus.wsserver.domain.GameLog;
import com.zylear.blokus.wsserver.service.GameLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/3/10.
 */
@Component
public class GameLogServiceImpl implements GameLogService {

    private GameLogMapper gameLogMapper;

    @Override
    public void insert(GameLog gameLog) {
        gameLogMapper.insert(gameLog);
    }

    @Override
    public GameLog findById(Integer gameLogId) {
        return gameLogMapper.selectByPrimaryKey(gameLogId);
    }

    @Autowired
    public void setGameLogMapper(GameLogMapper gameLogMapper) {
        this.gameLogMapper = gameLogMapper;
    }
}
