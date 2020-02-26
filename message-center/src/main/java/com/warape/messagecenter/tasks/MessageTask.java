package com.warape.messagecenter.tasks;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.warape.messagecenter.entity.MessageInfo;
import com.warape.messagecenter.message.OrderSenderProcess;
import com.warape.messagecenter.services.IMessageInfoService;
import com.warape.messagecenter.utils.RabbitUtil;
import com.warape.producer.services.IPayInfoService;
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
    private IMessageInfoService messageInfoService;
    @Autowired
    private OrderSenderProcess orderSenderProcess;
    @Autowired
    private IPayInfoService payInfoService;

    /**
     * 10分钟执行一次
     */
    @Scheduled(fixedDelay = 1000*60*10)
    public void confirmState(){
        //定时轮训长时间没有被更新状态为可发送的信息  确认业务是否成功
        LambdaQueryWrapper<MessageInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MessageInfo::getState,MessageConstants.MessageStateEnum.BE_SEND.getState());
        List<MessageInfo> list = messageInfoService.list(queryWrapper);
        for (MessageInfo messageInfo : list) {
            //订单ID
            String messageId = messageInfo.getMessageId();
            if (payInfoService.confirmPayState(Integer.valueOf(messageId))) {
                //支付成功
                String handlerJson = messageInfo.getHandlerJson();
                orderSenderProcess.send(new DirectExchange(RabbitConstants.ORDER_EXCHANGE_NAME),RabbitConstants.ORDER_ROUTING_KEY,handlerJson,messageId);
            }
        }
    }

    /**
     * 3秒执行一次
     */
    @Scheduled(fixedDelay = 1000*3)
    public void beSendToMq(){
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
