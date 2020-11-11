package com.zylear.blokus.wsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class BlokusWebSocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlokusWebSocketServerApplication.class, args);
    }

}
