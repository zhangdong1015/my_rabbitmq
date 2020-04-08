package com.example.rabbitmq_demo1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName RabbitMqTests
 * @Description TODO
 * @Author dz
 * @Date 2020/4/7 20:12
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RabbitMqTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试直接模式发送
     */
    @Test
    public void sendDirect() {
        String message = "direct message";
        rabbitTemplate.convertAndSend(RabbitConsts.QUEUE_LOG_PRINT, message);
        log.info("消息发送成功：[{}]", message);
    }

    /**
     * 测试主题模式发送
     */
    @Test
    public void sendTopic() {
        String message = "topic message";
        rabbitTemplate.convertAndSend(RabbitConsts.TOPIC_MODE_QUEUE, "topic.queue", message);
        log.info("消息发送成功：[{}]", message);
    }


}


