package com.example.jms.jmsspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import com.example.jms.jmsspring.producer.MessageSender;

@SpringBootApplication
@Import({ MessageConfiguration.class, MessageListenerConfiguration.class })
public class JmsSpringApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(JmsSpringApplication.class, args);

		MessageSender messageSender = applicationContext.getBean(MessageSender.class);
		int i = 0;
		while (i < 5) {
			messageSender.sendMessage("kaise ho bhai...." + i);
			System.out.println("Message sent .. ");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}

		System.out.println("Produced produced all messages successfully..");

//		MessageReceiver receiver = applicationContext.getBean(MessageReceiver.class);
//		String message = receiver.receiveMessage();
//		
//		System.out.println("Message received success --> " + message);

		applicationContext.close();
	}

}
