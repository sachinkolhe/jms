package com.example.jms.jmsspring;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import com.example.jms.jmsspring.consumer.MessageReceiver;

@Configuration
public class MessageConfiguration {

	private static String brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static String queueName = "APNA_MESSAGE_QUEUE";
	@Autowired
	private MessageReceiver messageListener;

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
		connectionFactory.setBrokerURL(brokerURL);
		connectionFactory.setTrustAllPackages(true);
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setDefaultDestinationName(queueName);
		return jmsTemplate;
	}
	
	@Bean
	public MessageConverter messageConverter() {
		return new SimpleMessageConverter();
	}
	
	
}
