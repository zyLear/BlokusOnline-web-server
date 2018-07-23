package com.zylear.blokus.wsserver.server;

import com.zylear.blokus.wsserver.handler.HttpRequestHandler;
import com.zylear.blokus.wsserver.handler.TextWebSocketFrameHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by lichao01 on 2018/7/6.
 */
@Component
public class BlokusServerChannelInitializer extends ChannelInitializer<SocketChannel> {



    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new IdleStateHandler(60, 365 * 60 * 60 * 24, 0, TimeUnit.SECONDS));
        // HttpServerCodec：将请求和应答消息解码为HTTP消息
        pipeline.addLast(new HttpServerCodec());
        // ChunkedWriteHandler：向客户端发送HTML5文件
        // pipeline.addLast(new ChunkedWriteHandler());
        // HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new HttpRequestHandler());
        pipeline.addLast(new WebSocketServerProtocolHandler("/blokus"));
//        pipeline.addLast(msgCheckHandler);

        pipeline.addLast(new TextWebSocketFrameHandler());

    }
}
