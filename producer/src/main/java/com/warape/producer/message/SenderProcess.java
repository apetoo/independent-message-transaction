package com.warape.producer.message;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.warape.producer.entity.LocalMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wanmingyu
 */
@Component
@Slf4j
public class SenderProcess implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("ack:{} cause:{}", ack, cause);
        String id = correlationData.getId();
        LocalMessage localMessage = new LocalMessage();
        localMessage.setState(1);
        localMessage.update(new LambdaUpdateWrapper<LocalMessage>().eq(LocalMessage::getUuid, id));
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

    }
}
