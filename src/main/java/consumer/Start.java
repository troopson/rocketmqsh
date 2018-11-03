/**
 * 
 */
package consumer;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author qujianjun   troopson@163.com
 * 2018年11月2日 
 */
public class Start {

    public static void main(String[] args) throws InterruptedException, MQClientException {
 
    	if( args.length != 3 && args.length!=4) { 
    		System.out.println("Usage: ");
    		System.out.println(" sync consumer_id, address topic [tag]");
    		return;
    	}
    	
    	String consumerid = args[0].trim();
    	String address = args[1].trim();
    	String topic = args[2].trim();     	
    	String tag = null;
    	if(args.length == 4)
    		args[3].trim(); 
    	
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerid);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);  
        consumer.setNamesrvAddr(address);
        
        // Subscribe one more more topics to consume.
        consumer.subscribe(topic, "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
            	for(MessageExt me: msgs) {
            		String  tg = me.getTags();
            		if(tag==null || "".equals(tag) || tg.contains(tag))
            			System.out.println(new String(me.getBody())); 
            	}
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
 
        consumer.start();
 
    }
}