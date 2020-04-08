package com.example.rabbitmq_demo1;

/**
 * @ClassName RabbitConsts
 * @Description TODO
 * @Author dz
 * @Date 2020/4/7 18:57
 * @Version 1.0
 */
public class RabbitConsts
{
    /**
     * 分列模式
     */
    public final static String FANOUT_MODE_QUEUE = "fanout.mode";

    /**
     * 日志打印队列
     */
    public final static String QUEUE_LOG_PRINT = "queue.log.recode";

    /**
     * 主题模式
     */
    public final static String TOPIC_MODE_QUEUE = "topic.mode";

    /**
     * 主题模式
     */
    public final static String TOPIC_ROUTING_KEY = "topic.*";


}
