package com.zylear.blokus.wsserver.dao.mybatis.blokusgame;


import com.zylear.blokus.wsserver.domain.GameAccount;
import org.apache.ibatis.annotations.Param;

public interface GameAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameAccount record);

    int insertSelective(GameAccount record);

    GameAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameAccount record);

    int updateByPrimaryKey(GameAccount record);


    GameAccount findByAccountAndPassowrd(@Param("account") String account,
                                         @Param("password") String password);

    GameAccount findByAccount(@Param("account") String account);
}