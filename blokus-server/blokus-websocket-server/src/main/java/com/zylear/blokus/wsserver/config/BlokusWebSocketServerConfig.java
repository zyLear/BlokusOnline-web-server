package com.zylear.blokus.wsserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;

/**
 * Created by xiezongyu on 2018/7/18.
 */
@Configuration
public class BlokusWebSocketServerConfig {

    @Bean
    public Integer websocketPort(@Value("${websocket.port}") Integer websocketPort) {
        return websocketPort;
    }

//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public Client zookeeperClient(
//            @Value("${zookeeper.connectstring}") String connectString,
//            @Value("${zookeeper.locks.path}") String basePath) {
//        return new Client(connectString, basePath);
//    }
//
//    @Bean(initMethod = "closeIdleStart", destroyMethod = "destory")
//    public HttpClientManagerV2 httpClientManagerV2() {
//        HttpClientManagerV2 httpClientManager = new HttpClientManagerV2(
//                3000, 30000, new HashSet<String>(), null, null
//        );
//        httpClientManager.setMaxTotal(1023);
//        httpClientManager.setIsUseV1(false);
//        return httpClientManager;
//    }

//    @Bean
//    public WxpushSvcManager wxpushSvcManager(HttpClientManagerV2 httpClientManagerV2,
//                                             @Value("${wxpush.host}") String wxpushHost,
//                                             @Value("${wxpush.validateWxIdUri}") String validateWxIdUri ) {
//
//        WxpushSvcManager wxpushSvcManager = new WxpushSvcManager();
//        wxpushSvcManager.setHttpClientManagerV2(httpClientManagerV2);
//        wxpushSvcManager.setHost(wxpushHost);
//        wxpushSvcManager.setValidateWxIdUri(validateWxIdUri);
//        return wxpushSvcManager;
//    }

}
