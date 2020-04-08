package com.example.rabbitmq_demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 通配匹配
 * # 一个或多个
 * * 只会匹配一个
 * @ClassName ProducerTopicExchangefetch
 * @Description TODO
 * @Author dz
 * @Date 2020/4/8 20:53
 * @Version 1.0
 */
public class ProducerTopicExchange
{
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置host
        connectionFactory.setHost("111.229.240.182");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHandshakeTimeout(20000);

        //建立连接
        Connection connection = connectionFactory.newConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //交换机名称
        String exchangeName ="test_topic_exchange";
        String routeKey1 ="user.save";
        String routeKey2 ="user.update";
        String routeKey3 = "user.delete.asc";

        String msg ="Hello World RabbitMQ4 Direct Exchange Message";
        channel.basicPublish(exchangeName,routeKey1,null,msg.getBytes());
        channel.basicPublish(exchangeName,routeKey2,null,msg.getBytes());
        channel.basicPublish(exchangeName,routeKey3,null,msg.getBytes());
    }
}
