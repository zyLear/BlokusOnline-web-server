package com.zylear.blokus.wsserver.service;



import com.zylear.blokus.wsserver.domain.PlayerGameLog;

import java.util.List;

/**
 * Created by xiezongyu on 2018/3/10.
 */
public interface PlayerGameLogService {

    void insert(PlayerGameLog playerGameLog);

    List<PlayerGameLog> findByAccount(String account);

    List<PlayerGameLog> findByGameLogId(Integer gameLogId);
}
