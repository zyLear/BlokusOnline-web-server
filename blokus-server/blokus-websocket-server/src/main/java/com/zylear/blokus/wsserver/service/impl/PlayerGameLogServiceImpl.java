package com.zylear.blokus.wsserver.service.impl;

import com.zylear.blokus.wsserver.dao.mybatis.blokusgame.PlayerGameLogMapper;
import com.zylear.blokus.wsserver.domain.PlayerGameLog;
import com.zylear.blokus.wsserver.service.PlayerGameLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/3/10.
 */
@Component
public class PlayerGameLogServiceImpl implements PlayerGameLogService {

    private PlayerGameLogMapper playerGameLogMapper;


    @Override
    public void insert(PlayerGameLog playerGameLog) {
        playerGameLogMapper.insert(playerGameLog);
    }

    @Override
    public List<PlayerGameLog> findByAccount(String account) {
        return playerGameLogMapper.findByAccount(account);
    }

    @Override
    public List<PlayerGameLog> findByGameLogId(Integer gameLogId) {
        return playerGameLogMapper.findByGameLogId(gameLogId);
    }


    @Autowired
    public void setPlayerGameLogMapper(PlayerGameLogMapper playerGameLogMapper) {
        this.playerGameLogMapper = playerGameLogMapper;
    }
}
