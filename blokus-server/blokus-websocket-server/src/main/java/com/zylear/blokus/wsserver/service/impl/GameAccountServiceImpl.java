package com.zylear.blokus.wsserver.service.impl;

import com.zylear.blokus.wsserver.dao.mybatis.blokusgame.GameAccountMapper;
import com.zylear.blokus.wsserver.domain.GameAccount;
import com.zylear.blokus.wsserver.service.GameAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/3/7.
 */
@Component
public class GameAccountServiceImpl implements GameAccountService {

    private GameAccountMapper gameAccountMapper;

    @Override
    public GameAccount findByAccountAndPassowrd(String account, String password) {
        return gameAccountMapper.findByAccountAndPassowrd(account, password);
    }

    @Override
    public void insert(GameAccount gameAccount) {
        gameAccountMapper.insert(gameAccount);
    }

    @Override
    public GameAccount findByAccount(String account) {
        return gameAccountMapper.findByAccount(account);
    }


    @Autowired
    public void setGameAccountMapper(GameAccountMapper gameAccountMapper) {
        this.gameAccountMapper = gameAccountMapper;
    }


}
