package com.zylear.blokus.wsserver.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by xiezongyu on 2018/7/18.
 */
@Component
public class BlokusServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BlokusServer.class);
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    @Autowired
    private Integer websocketPort;
    @Autowired
    private BlokusServerChannelInitializer channelInitializer;

    public void start() {
        new Thread(this).start();
    }

    public void close() {
        //释放线程池资源
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    @PostConstruct
    public void init() {
        start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                close();
            }
        });
    }

    @Override
    public void run() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioServerSocketChannel.class)
                .childHandler(channelInitializer);
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(websocketPort).sync();
            logger.info("blokus server startup success.");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("blokus server closed the exception :{}", e.fillInStackTrace());
        }
    }


}
