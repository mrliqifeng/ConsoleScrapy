package com.njci.student.util;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ScrapyRabbitPro {
	//¶ÓÁÐÃû
    private final static String QUEUE_NAME = "javapython";
    private Channel channel;
    private static ScrapyRabbitPro sendRabbit;
    public static ScrapyRabbitPro getSendRabbit(){
        if(sendRabbit==null){
            try {
				sendRabbit = new ScrapyRabbitPro();
			} catch (IOException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return sendRabbit;
    }
    private ScrapyRabbitPro() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
//        factory.setConnectionTimeout(2);
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
    }
    public void send(JSONObject message){
        try {
			channel.basicPublish("", QUEUE_NAME, null, message.toString().getBytes("utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Producer Send +'" + message + "'");
    }
    public static void main(String[] args)  {
    	ScrapyRabbitPro scPro = ScrapyRabbitPro.getSendRabbit();
    	JSONObject message = new JSONObject();
    	message.put("method", "stop");
    	message.put("taskid", 1);
		scPro.send(message);
	}
}


