package com.warape.consumer.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.rabbitmq.client.Channel;
import com.warape.consumer.entity.OrderInfo;
import com.warape.consumer.services.IOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.warape.commons.constants.OrderConstants;
import org.warape.commons.constants.RabbitConstants;
import org.warape.commons.dto.SendMessageResult;

import java.io.IOException;
import java.util.Objects;

/**
 * @author wanmingyu
 */
@Component
@Slf4j
public class OrderConsumerHandler {

    @Autowired
    private IOrderInfoService orderInfoService;

    @RabbitListener(queues = RabbitConstants.ORDER_QUEUE_NAME)
    public void orderState(Message message, Channel channel) throws IOException {
        String result = new String(message.getBody());
        SendMessageResult sendMessageResult = JSON.parseObject(result, SendMessageResult.class);
        //订单ID
        String uniqueId = sendMessageResult.getUniqueId();
        //先查询
        OrderInfo byId = orderInfoService.getById(uniqueId);
        if (Objects.isNull(byId)) {
            //如果为空  说明无此订单 丢弃返回
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            return;
        }

        //修改
        LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OrderInfo::getId, result).set(OrderInfo::getState, OrderConstants.OderStateEnum.YES_PAYMENT.getState());
        if (orderInfoService.update(updateWrapper)) {
            //成功
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } else {
            //失败
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }

    }
}
