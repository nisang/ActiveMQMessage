package io.dandan.message;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

/**
 * 点对点的Queue消息类型的监听
 * @author Administrator
 *
 */
@Component
public class MessageQueueReceiver implements MessageListener{

	@Override
	public void onMessage(Message message) {
		
		System.out.println("--------------------MessageQueueReceiver");
		
	}

}
