package com.warape.messagecenter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author wanmingyu
 */
@Component
public class RabbitUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(RabbitUtil.class);
    
    private final RabbitAdmin rabbitAdmin;
    
    private final RabbitTemplate rabbitTemplate;
    
    public RabbitUtil(RabbitAdmin rabbitAdmin, RabbitTemplate rabbitTemplate){
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 转换Message对象
     * @param messageType 返回消息类型 MessageProperties类中常量
     * @param msg
     * @return
     */
    public static Message getMessage(String messageType, Object msg){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(messageType);

        Message message = new Message(msg.toString().getBytes(),messageProperties);
        return message;
    }

    /**
     * 有绑定Key的Exchange发送
     * @param routingKey
     * @param messageId
     * @param msg
     */
    public void sendMessageToExchange(Exchange customExchange, String routingKey, Object msg,String messageId){
        Message message = getMessage(MessageProperties.CONTENT_TYPE_JSON,msg);
        CorrelationData correlationData = new CorrelationData(messageId);
        rabbitTemplate.send(customExchange.getName(), routingKey, message,correlationData);
    }

    /**
     * 有绑定Key的Exchange发送
     * @param routingKey
     * @param msg
     */
    public void sendMessageToExchange(Exchange customExchange, String routingKey, Object msg){
        Message message = getMessage(MessageProperties.CONTENT_TYPE_JSON,msg);
        rabbitTemplate.convertAndSend(customExchange.getName(), routingKey, message);
    }

    /**
     * 有绑定Key的Exchange发送 设置过期时间 根据插件方案
     * @param routingKey
     * @param msg
     */
    public void sendMessageToExchangePlugin(String exchangeName, String routingKey, Object msg, long expiration, TimeUnit unit){
        expiration = TimeUnit.MILLISECONDS.convert(expiration, Optional.ofNullable(unit).orElse(TimeUnit.MILLISECONDS));
        Message message = getMessage(MessageProperties.CONTENT_TYPE_JSON,msg);
        message.getMessageProperties().setHeader("x-delay",expiration);
        rabbitTemplate.send(exchangeName, routingKey, message);
    }

}
