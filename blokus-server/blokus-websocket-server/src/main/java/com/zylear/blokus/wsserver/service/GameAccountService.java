package com.zylear.blokus.wsserver.service;


import com.zylear.blokus.wsserver.domain.GameAccount;

/**
 * Created by xiezongyu on 2018/3/7.
 */
public interface GameAccountService {

    GameAccount findByAccountAndPassowrd(String account, String password);

    void insert(GameAccount gameAccount);

    GameAccount findByAccount(String account);
}
