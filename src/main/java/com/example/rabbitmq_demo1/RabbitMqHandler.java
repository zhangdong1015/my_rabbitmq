package com.example.rabbitmq_demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName RabbitMqHandler
 * @Description TODO
 * @Author dz
 * @Date 2020/4/7 20:03
 * @Version 1.0
 */
@Slf4j
@Component
public class RabbitMqHandler
{
    @RabbitListener(queues = RabbitConsts.QUEUE_LOG_PRINT)
    public void queueLogPrintHandler(String message) {
        log.info("接收到操作日志记录消息：[{}]", message);
    }

    /**
     * 主题模式处理handler
     *
     * @param message 待处理的消息体
     */
    @RabbitListener(queues = RabbitConsts.TOPIC_ROUTING_KEY)
    public void queueTopicHandler(String message) {
        log.info("主题模式处理器，接收消息：[{}]", message);
    }


}
