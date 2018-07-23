package com.zylear.blokus.wsserver.handler;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by xiezongyu on 2018/7/16.
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private Logger loger = LoggerFactory.getLogger(HttpRequestHandler.class);
    //    @Autowired
//    private WechatRobotUserService wechatRobotUserService;
    private String webUri = "/blokus";
//    private WxpushSvcManager wxpushSvcManager;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        loger.info("request url: {}, {}", webUri, request.uri());

        String uri = StringUtils.substringBefore(request.uri(), "?");
        if (webUri.equalsIgnoreCase(uri)) {//获取webSocket参数
            QueryStringDecoder query = new QueryStringDecoder(request.uri());
            Map<String, List<String>> map = query.parameters();
            List<String> tokens = map.get("token");

            //根据参数保存当前登录对象, 并把该token加入到channel中
//            if (tokens != null && !tokens.isEmpty()) {
//                String token = tokens.get(0);
////                WechatRobotUser wechatRobotUser = wechatRobotUserService.findByWxWechatId(token);
//                if (!wxpushSvcManager.validateWxId(token) || ServerCache.existClient(token)) {
//                    ctx.channel().close();
//                    return;
//                }
//            }
            request.setUri(uri);
            ctx.fireChannelRead(request.retain());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

//    private void send100ContinueExpected(ChannelHandlerContext ctx) {
////        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONFLICT);
////        ctx.writeAndFlush(response);
//    }



}
