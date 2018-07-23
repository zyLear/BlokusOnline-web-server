package com.zylear.blokus.wsserver.manager.basehandler;

/**
 * Created by xiezongyu on 2018/2/2.
 */
public interface MessageHandler<T, K> {

    void handle(T t, K k);

    void send(K t);
}
