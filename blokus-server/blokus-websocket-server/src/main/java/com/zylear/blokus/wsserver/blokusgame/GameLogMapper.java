package com.zylear.blokus.wsserver.blokusgame;


import com.zylear.blokus.wsserver.domain.GameLog;

public interface GameLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameLog record);

    int insertSelective(GameLog record);

    GameLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameLog record);

    int updateByPrimaryKey(GameLog record);
}