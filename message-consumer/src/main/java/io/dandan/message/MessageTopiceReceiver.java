package io.dandan.message;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

/**
 * 点对点的Topic消息监听类型
 * @author Administrator
 *
 */
@Component
public class MessageTopiceReceiver implements MessageListener{

	@Override
	public void onMessage(Message message) {
		System.out.println("-----------------MessageTopiceReceiver");
	}

}
