package net.yarn.hsf.demo1.service;

import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessagingService {
	
	@Value("${mq.nameserver.address}")
	private String nameServerAddress;

	@Value("${mq.producer.group}")
	private String producerGroup;
	
	public void send(String topic, String tag, String message) {

		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
		// Specify name server addresses.
		producer.setNamesrvAddr(nameServerAddress);
		
		
		try {
			// Launch the instance.
			producer.start();

			// Create a message instance, specifying topic, tag and message body.
			Message msg = new Message(topic, tag, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
			// Call send message to deliver message to one of brokers.
			SendResult sendResult = producer.send(msg);
			
			log.debug("The send result is {}", sendResult);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (MQBrokerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		producer.shutdown();
	}

}
