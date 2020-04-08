package com.example.rabbitmq_demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Procuder
 * @Description TODO
 * @Author dz
 * @Date 2020/4/8 12:05
 * @Version 1.0
 */
public class Procuder
{
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("111.229.240.182");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/"); //逻辑虚拟机
        connectionFactory.setHandshakeTimeout(20000);
        //使用连接工厂建立连接
        Connection connection = connectionFactory.newConnection();

        //通过连接建立通道
        /**
         * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
         * exchange:指定交换机 不指定 则默认 （AMQP default交换机） 通过routingkey进行匹配
         * props 消息属性
         * body 消息体
         */
        Channel channel = connection.createChannel();
        for (int i = 0; i <100000 ; i++) {
            System.out.println("生产消息:" + i);
            String msg = "Hello RabbitMQ" + i;
            channel.basicPublish("","test",null,msg.getBytes());
        }
        //5.记得关闭相关的连接
        channel.close();
        connection.close();
    }

}
