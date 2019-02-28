package com.njci.student.util;
import com.rabbitmq.client.*;
import javax.swing.*;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class ScrapyRabbitCon{
	//队列名
    private final static String QUEUE_NAME = "pythonjava";
    private static ScrapyRabbitCon rabbitmq;

    public static ScrapyRabbitCon getRabbit() {
        if(rabbitmq==null){
            try {
				rabbitmq = new ScrapyRabbitCon();
			} catch (IOException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return rabbitmq;
    }
    private ScrapyRabbitCon() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        factory.setConnectionTimeout(2);
        factory.setChannelRpcTimeout(1000000000);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                //此处采用Swing弹窗显示接收到的消息
                JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println(message);
//                try {
//                    System.out.println("Customer Received '" + message + "'");
//                    JSONObject jsonObject = new JSONObject(message);
//                    Map<String,Integer> map = new HashMap<>();
//                    for (Iterator it = jsonObject.keys(); it.hasNext(); ) {
//                        String key = (String) it.next();
//                        Integer value = (Integer) jsonObject.get(key);
//                        map.put(key,value);
//                    }
//                    for(String string:map.keySet()){
//                        System.out.println("key:"+string);
//                        System.out.println("value:"+map.get(string));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        };
        channel.basicConsume(QUEUE_NAME,true, consumer);
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        ScrapyRabbitCon scPro = new ScrapyRabbitCon();
    	
    }
}