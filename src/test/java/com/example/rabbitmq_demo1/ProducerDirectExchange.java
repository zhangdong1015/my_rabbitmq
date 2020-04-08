package com.example.rabbitmq_demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 默认交换机生产者
 * @ClassName ProducerDirectExchange
 * @Description TODO
 * @Author dz
 * @Date 2020/4/8 19:37
 * @Version 1.0
 */
public class ProducerDirectExchange
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

        //声明交换机的名称
        String  exchangeName  = "test_direct_exchange";
        String routeKey = "test.direct";
        String msg = "Hello World RabbitMQ4 Direct Exchange Message";
        //basicPublish  4个参数 交换机名称  路由名称 配置文件
        channel.basicPublish(exchangeName,routeKey,null,msg.getBytes());


    }
}
