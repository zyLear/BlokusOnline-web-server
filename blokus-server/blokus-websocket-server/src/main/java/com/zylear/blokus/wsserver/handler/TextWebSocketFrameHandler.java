package com.zylear.blokus.wsserver.handler;


import com.zylear.blokus.wsserver.bean.transfer.base.MessageBean;
import com.zylear.blokus.wsserver.bean.transfer.base.TransferBean;
import com.zylear.blokus.wsserver.cache.ServerCache;
import com.zylear.blokus.wsserver.manager.basehandler.MessageMaster;
import com.zylear.blokus.wsserver.queue.MessageQueue;
import com.zylear.blokus.wsserver.util.JsonUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiezongyu on 2018/7/16.
 */

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final Logger logger = LoggerFactory.getLogger(TextWebSocketFrameHandler.class);

    private MessageMaster messageMaster;

    public TextWebSocketFrameHandler(MessageMaster messageMaster) {
        this.messageMaster = messageMaster;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        MessageBean messageBean = JsonUtil.getObjectFromJson(msg.text(), MessageBean.class);
        messageMaster.addCommand(new TransferBean(ctx.channel(), messageBean));
//        MessageQueue.getInstance().put();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        messageMaster.addCommand(new TransferBean(ctx.channel(), MessageBean.QUIT));
//        MessageQueue.getInstance().put(new TransferBean(ctx.channel(), MessageBean.QUIT));
        logger.info("client:{} channelInactive. ", channel.remoteAddress());
        ctx.close();
        //  channelInactive  ->  handlerRemoved
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        Channel channel = ctx.channel();
        messageMaster.addCommand(new TransferBean(ctx.channel(), MessageBean.QUIT));
//        MessageQueue.getInstance().put(new TransferBean(ctx.channel(), MessageBean.QUIT));
        logger.info("client:{} exceptionCaught. ", channel.remoteAddress(), e);
        // 当出现异常就关闭连接
        ctx.close();
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        Channel channel = ctx.channel();
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                logger.info("client:{} channelInactive. ", channel.remoteAddress());
                ctx.close();
            }/* else if (e.state() == IdleState.WRITER_IDLE) {
                ctx.writeAndFlush(new PingMessage());
            }*/
        }
    }

}
