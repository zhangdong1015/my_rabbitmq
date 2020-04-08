package com.example.rabbitmq_demo1;

import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitMqConfig
 * @Description TODO
 * @Author dz
 * @Date 2020/4/7 18:59
 * @Version 1.0
 */
@Slf4j
@Configuration
public class RabbitMqConfig
{
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory)
    {
        //连接工程设置参数
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData[{}],ack[{}],cause[{}]", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange[{}],route[{}],replyCode[{}],replyText[{}],message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;

    }


    @Bean
    public Queue logprintQueue()
    {
        return  new Queue(RabbitConsts.QUEUE_LOG_PRINT) ;
    }

    @Bean
    public Queue  topicQueue()
    {
        return  new Queue (RabbitConsts.TOPIC_ROUTING_KEY);
    }
    @Bean
    public TopicExchange topicExchange()
    {
        return  new TopicExchange(RabbitConsts.TOPIC_MODE_QUEUE);
    }



    @Bean
    public Binding topicBinding(@Qualifier("topicQueue") Queue topicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with(RabbitConsts.TOPIC_ROUTING_KEY);
    }







}
