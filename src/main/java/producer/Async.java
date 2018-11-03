/**
 * 
 */
package producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author qujianjun   troopson@163.com
 * 2018年11月2日 
 */
public class Async {
	
	  public static void main(String[] args) throws Exception {
	    	if( args.length != 4) {
	    		System.out.println("Usage: ");
	    		System.out.println(" sync address topic tag content");
	    		return;
	    	}
	    	String address = args[0].trim();
	    	String topic = args[1].trim(); 
	    	String tag = args[2].trim();
	    	String content = args[3].trim();
	    	
	    	if(content.length()==0) {
	    		System.out.println("Content cannot be null.");
	    		return;
	    	}
	    	
	    		 
	        DefaultMQProducer producer = new  DefaultMQProducer(Thread.currentThread().getName()+"_sync");
	        // Specify name server addresses.
	        producer.setNamesrvAddr(address);
	        //Launch the instance.
	        producer.start();  
	        Message msg = new Message(topic, tag, content.getBytes(RemotingHelper.DEFAULT_CHARSET)  ); 
	        producer.send(msg,new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("OK!");
                }
                @Override
                public void onException(Throwable e) { 
                    e.printStackTrace();
                }
            }); 
	        producer.shutdown();
	    }
	  
}
