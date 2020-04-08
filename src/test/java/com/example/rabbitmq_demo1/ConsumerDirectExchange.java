package com.example.rabbitmq_demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * https://www.cnblogs.com/dwlovelife/p/10982735.html
 * 默认交换机模式
 * @ClassName ConsumerDirectExchange
 * @Description TODO
 * @Author dz
 * @Date 2020/4/8 19:57
 * @Version 1.0
 */
public class ConsumerDirectExchange
{
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //创建连接工厂
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
        String exchangeName = "test_direct_exchange";
        String exchangeType ="direct";
        String queueName ="test_direct_queue";
        String routeKey ="test.direct";

        //声明一个交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);
        //声明一个队列
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName,exchangeName,routeKey);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //设置消费基本信息
        channel.basicConsume(queueName,true,consumer);

        while (true)
        {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String (delivery.getBody());
            System.out.println("收到的消息:" + msg);
        }
    }
}
