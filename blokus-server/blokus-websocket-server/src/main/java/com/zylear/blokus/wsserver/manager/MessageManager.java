package com.zylear.blokus.wsserver.manager;


import com.zylear.blokus.wsserver.bean.RegisterMsg;
import com.zylear.blokus.wsserver.bean.base.MessageBean;
import com.zylear.blokus.wsserver.bean.base.TransferBean;
import com.zylear.blokus.wsserver.constant.MsgType;
import com.zylear.blokus.wsserver.manager.basehandler.MessageHandler;
import com.zylear.blokus.wsserver.util.JsonUtil;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/7/18.
 */
@Component
public class MessageManager implements MessageHandler<TransferBean, List<TransferBean>> {

    private static final Logger logger = LoggerFactory.getLogger(MessageManager.class);


    @Override
    public void handle(TransferBean transferBean, List<TransferBean> responses) {
        MessageBean messageBean = transferBean.getMessageBean();
        logger.info("handle msg :" + messageBean);
        switch (messageBean.getMsgType()) {
            case MsgType.REGISTER:
                register(transferBean, responses);
                break;
            case MsgType.PING:
                //do nothing
                break;
            default:
                break;
        }
    }


    private void register(TransferBean transferBean, List<TransferBean> responses) {
//        RegisterMsg registerMsg = JsonUtil.getObjectFromJson(transferBean.getMessageBean().getBody(), RegisterMsg.class);
        MessageBean response = null;
//        WechatRobotUser wechatRobotUser = wechatRobotUserService.findByWxWechatId(registerMsg.getWxId());

//        if (!wxpushSvcManager.validateWxId(registerMsg.getWxId()) || ServerCache.existClient(registerMsg.getWxId())) {
//            response = MessageFormatter.formatMessage(MsgType.REGISTER_RESPONSE, RegisterRespMsg.ERROR_RESPONSE);
//        } else {
//            ServerCache.addChannel(registerMsg.getWxId(), transferBean.getChannel());
//            response = MessageFormatter.formatMessage(MsgType.REGISTER_RESPONSE, RegisterRespMsg.SUCCESS_RESPONSE);
//        }
        responses.add(new TransferBean(transferBean.getChannel(), response));
    }

    @Override
    public void send(List<TransferBean> transferBeans) {
        if (transferBeans != null) {
            for (TransferBean transferBean : transferBeans) {
//                transferBean.getChannel().writeAndFlush(new TextWebSocketFrame(JsonUtil.format(transferBean.getMessageBean())));
            }
        }
    }


}
