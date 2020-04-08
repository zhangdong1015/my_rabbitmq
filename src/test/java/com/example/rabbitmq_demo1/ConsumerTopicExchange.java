package com.example.rabbitmq_demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ConsumerTopicExchange
 * @Description TODO
 * @Author dz
 * @Date 2020/4/8 21:05
 * @Version 1.0
 */
public class ConsumerTopicExchange
{
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置host
        connectionFactory.setHost("111.229.240.182");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setHandshakeTimeout(20000);

        //建立连接
        Connection connection = connectionFactory.newConnection();

        //创建通道
        Channel channel = connection.createChannel();
        //交换机
        String exchangeName = "test_topic_exchange";
        String  exchangeType ="topic";
        String queueName  ="test_topic_queue";
        String routingKey = "user.#";
        //声明交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);

        //声明队列
        channel.queueDeclare(queueName,false,false,false,null);

        //建立绑定关系
        channel.queueBind(queueName,exchangeName,routingKey);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName,true,consumer);
        //循环获取消息
        while(true){
            //获取消息,如果没有消息,这一步将会一直阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("收到消息:" + msg);
        }
    }
}
