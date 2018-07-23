package com.zylear.blokus.wsserver.clientdemo;

import com.zylear.blokus.wsserver.bean.base.MessageBean;
import com.zylear.blokus.wsserver.util.JsonUtil;
import okhttp3.*;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xiezongyu on 2018/7/18.
 */
public class OkhttpWebsocketClient {

//    private static String hostName = "172.22.15.8";
    private static String hostName = "apiws-dev.changingedu.com";
    private static Integer port = 8081;
    public static WebSocket mWebSocket;

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        String wsUrl = "wss://" + hostName + /*":" + port + */"/wxbroker?token=wxid_9z2nmq99pq4722";
        //新建client
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        //构造request对象
        Request request = new Request.Builder()
                .url(wsUrl)
                .build();
        //建立连接
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                mWebSocket = webSocket;
                System.out.println("client onOpen");
                System.out.println("client request header:" + response.request().headers());
                System.out.println("client response header:" + response.headers());
                System.out.println("client response:" + response);
                //开启消息定时发送

                mWebSocket.send("{\"header\":{\"version\":\"v1\",\"uuid\":\"789b3b44-561b-42eb-85d7-0ab0b224c201\",\"msgType\":1},\"body\":\"{\\n  \\\"wxId\\\": \\\"wxid_9z2nmq99pq4722\\\"\\n}\"}");

                startTask();
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                System.out.println("client onMessage");
//                System.out.println("message:" + text);

                System.out.println(JsonUtil.getObjectFromJson(text, MessageBean.class));
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                System.out.println("client onClosing");
                System.out.println("code:" + code + " reason:" + reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                System.out.println("client onClosed");
                System.out.println("code:" + code + " reason:" + reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                //出现异常会进入此回调
                System.out.println("client onFailure");
                System.out.println("throwable:" + t);
                System.out.println("response:" + response);
            }
        });
    }

    private static void startTask() {
        Timer mTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (mWebSocket == null) return;
//                msgCount++;
                boolean isSuccessed = mWebSocket.send("{\"header\":{\"version\":\"v1\",\"uuid\":\"789b3b44-561b-42eb-85d7-0ab0b224c201\",\"msgType\":3}}");
                //除了文本内容外，还可以将如图像，声音，视频等内容转为ByteString发送
                //boolean send(ByteString bytes);
            }
        };
        mTimer.schedule(timerTask, 0, 30000);
    }

}
