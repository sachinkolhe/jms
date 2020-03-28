package com.example.jms.jmsspring;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class MessageListenerConfiguration {

	@Autowired
	ConnectionFactory connectionFactory;
	@Bean 
	public DefaultJmsListenerContainerFactory messageListener() {
		
		DefaultJmsListenerContainerFactory container = new DefaultJmsListenerContainerFactory();
		container.setConnectionFactory(connectionFactory);
		container.setConcurrency("1-1");
		return container;
	}
}
