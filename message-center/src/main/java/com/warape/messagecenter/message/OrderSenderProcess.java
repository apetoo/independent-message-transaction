package com.warape.messagecenter.message;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.warape.messagecenter.entity.MessageInfo;
import com.warape.messagecenter.services.IMessageInfoService;
import com.warape.messagecenter.utils.RabbitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.warape.commons.constants.MessageConstants;

/**
 * @author wanmingyu
 */
@Component
@Slf4j
public class OrderSenderProcess implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {

    @Autowired
    private IMessageInfoService messageInfoService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 有绑定Key的Exchange发送
     *
     * @param routingKey
     * @param messageId
     * @param msg
     */
    public void send(Exchange customExchange, String routingKey, Object msg, String messageId) {
        // 当Mandatory参数设为true时，如果目的不可达，会发送消息给生产者，生产者通过一个回调函数来获取该信息。
        rabbitTemplate.setMandatory(true);
        //确认回调
        rabbitTemplate.setConfirmCallback(this);
        //失败回退
        rabbitTemplate.setReturnCallback(this);
        Message message = RabbitUtil.getMessage(MessageProperties.CONTENT_TYPE_JSON, msg);
        CorrelationData correlationData = new CorrelationData(messageId);
        rabbitTemplate.send(customExchange.getName(), routingKey, message, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("ack:{} cause:{}", ack, cause);
        String id = correlationData.getId();
        log.info("ID为:{}", id);
        if (ack) {
            //发送成功 更改状态  消费者确保幂等性
            LambdaUpdateWrapper<MessageInfo> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(MessageInfo::getMessageId, id)
                    .set(MessageInfo::getState, MessageConstants.MessageStateEnum.SEND_SUCCESS.getState());
            messageInfoService.update(wrapper);
        } else {
            //发送失败 定时器会重新发送
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("returnedMessage:{},{},{},{}:{}", message, replyCode, replyText, exchange, routingKey);
    }
}
