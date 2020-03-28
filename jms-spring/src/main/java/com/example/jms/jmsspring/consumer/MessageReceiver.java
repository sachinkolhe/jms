package com.example.jms.jmsspring.consumer;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver  //implements MessageListener
{
	
	@Autowired
	JmsTemplate jmsTemplate;
	@Autowired
	MessageConverter messageConverter;
	
	private static final String queueName = "APNA_MESSAGE_QUEUE";
	
//	@Override
//	public void onMessage(Message message) {
//	
//		try {
//			String response = (String) messageConverter.fromMessage(message);
//			System.out.println(" Message received ==>> " + response);
//		} catch (MessageConversionException | JMSException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
	
	// remove MessageListener for this method and comment out onMessage() Method.
	
	public String receiveMessage() {
		
		Message message = jmsTemplate.receive();
		String response = null;
		try {
			response = (String) messageConverter.fromMessage(message);
			System.out.println("Message received .. ==> " + response);
		} catch (MessageConversionException | JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@JmsListener(destination = queueName)
	public void receiveMessageThruoghJmsListener(String message) {

		System.out.println("Message received ==> " + message);

	}

}
