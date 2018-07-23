package com.zylear.blokus.wsserver.cache;

import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiezongyu on 2018/7/18.
 */
public class ServerCache {

    private static Map<String, Channel> map = new ConcurrentHashMap<>();

    public static void addChannel(String wxId, Channel channel) {
        map.put(wxId, channel);
    }

    public static Channel getChannel(String wxId) {
        if (StringUtils.isEmpty(wxId)) {
            return null;
        }
        return map.get(wxId);
    }

    public static void removeClient(String wxId) {
        map.remove(wxId);
    }

    public static void removeChannel(Channel channel) {
        String key = null;
        for (Map.Entry<String, Channel> entry : map.entrySet()) {
            if (entry.getValue().equals(channel)) {
                key = entry.getKey();
            }
        }
        if (key != null) {
            map.remove(key);
        }
    }

    public static Boolean existClient(String wxId) {
        return map.containsKey(wxId);
    }


}
