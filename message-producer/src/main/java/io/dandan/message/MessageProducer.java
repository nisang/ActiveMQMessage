package io.dandan.message;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.google.gson.Gson;

import io.dandan.message.entity.MessageTag;
import io.dandan.message.entity.MessageTopic;


/**
 * 消息的生产者
 * 	-发送消息
 * @author syp
 *
 */
public class MessageProducer {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);
	@Autowired
	private JmsTemplate jmsTemplate; 
	/**
	 * 发布点对点消息，一条消息只能被一个下消费者消费
	 * @param messageTopic
	 * @param tag
	 * @param messageMap
	 */
	public void sendQueueMessage(MessageTopic messageTopic, MessageTag tag, Map messageMap){
		final String message = new Gson().toJson(messageMap);
		logger.info("send message topic:{},message:{}",messageTopic, tag, message);
		
		ActiveMQQueue destination = new ActiveMQQueue();
		
		destination.setPhysicalName(messageTopic.toString());
		jmsTemplate.send(destination,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
	
	/**
     * 发送点对点消息,一条消息能被多个消费者消费
     * @param messageTopic
     * @param tag
     * @param messageMap
     */
    public void sendTopicMessage(MessageTopic messageTopic, MessageTag tag, Map messageMap) {
        final String message = new Gson().toJson(messageMap);
        logger.info("send message topic:{},tag:{},message:{}", messageTopic, tag, message);

        ActiveMQTopic destination = new ActiveMQTopic();
        destination.setPhysicalName(messageTopic.toString());
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[]{"message-producer.xml"});
		
		MessageProducer  messageProducer = applicationContext.getBean("messageProducer", MessageProducer.class);
		for( int i=0; i < 10; i++ ){
			Map map = new HashMap();
			map.put("id", i);
			messageProducer.sendQueueMessage(MessageTopic.SEARCHQUEUE, MessageTag.CARSOURCE_ADD_CAR, map);
			messageProducer.sendTopicMessage(MessageTopic.SEARCHTOPIC, MessageTag.CARSOURCE_ADD_CAR, map);
		}
		
		
	}
    
    
    
    
}
