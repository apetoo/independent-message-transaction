package com.warape.messagecenter.tasks;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.warape.messagecenter.entity.MessageInfo;
import com.warape.messagecenter.message.OrderSenderProcess;
import com.warape.messagecenter.services.IMessageInfoService;
import com.warape.messagecenter.utils.RabbitUtil;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.warape.commons.constants.MessageConstants;
import org.warape.commons.constants.RabbitConstants;

import java.util.List;

/**
 * @author wanmingyu
 */
@Component
public class MessageTask {

    @Autowired
    private RabbitUtil rabbitUtil;
    @Autowired
    private IMessageInfoService messageInfoService;
    @Autowired
    private OrderSenderProcess orderSenderProcess;

//    @Scheduled(fixedDelay = 1000*30)
    public void confirmState(){
        //定时轮训长时间没有被更新状态为可发送的信息  确认业务是否成功
    }

    @Scheduled(fixedDelay = 1000*1000)
    public void beSendToMq(){
        //定时轮训长时间没有被更新状态为可发送的信息  确认业务是否成功
        LambdaQueryWrapper<MessageInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MessageInfo::getState, MessageConstants.MessageStateEnum.WAIT_SEND.getState());
        List<MessageInfo> list = messageInfoService.list(queryWrapper);
        for (MessageInfo messageInfo : list) {
            String messageId = messageInfo.getMessageId();
            String handlerJson = messageInfo.getHandlerJson();
            orderSenderProcess.send(new DirectExchange(RabbitConstants.ORDER_EXCHANGE_NAME),RabbitConstants.ORDER_ROUTING_KEY,handlerJson,messageId);
        }
    }
}
