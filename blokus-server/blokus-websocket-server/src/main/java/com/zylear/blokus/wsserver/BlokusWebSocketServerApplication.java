package com.zylear.blokus.wsserver;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.util.Properties;

@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan("com.zylear.blokus.wsserver.*")
@EnableAutoConfiguration(exclude = {
////		DataSourceAutoConfiguration.class,
//		DataSourceTransactionManagerAutoConfiguration.class,
//		MybatisAutoConfiguration.class,
////		DispatcherServletAutoConfiguration.class, /**如果需要自定义servlet dispatch,需要exclude*/
////		ErrorMvcAutoConfiguration.class,
////		RabbitAutoConfiguration.class,
		DataSourceAutoConfiguration.class
})
public class BlokusWebSocketServerApplication {

	public static void main(String[] args) {
		Properties properties = System.getProperties();
		System.out.println(properties);
		SpringApplication.run(BlokusWebSocketServerApplication.class, args);
	}

//	@Override
//	public void customize(ConfigurableEmbeddedServletContainer container) {
//		container.setPort(8152);
////		container.setContextPath("/blokus");
//	}
}
