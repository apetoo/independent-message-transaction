package com.warape.producer.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.warape.messagecenter.services.IMessageInfoService;
import com.warape.producer.entity.PayInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.warape.commons.constants.CommonConstants;
import org.warape.commons.dto.SendMessageResult;
import org.warape.commons.repsonse.ResponseResult;

import java.math.BigDecimal;

/**
 * @author wanmingyu
 */
@RestController
public class PayController {

    @Reference
    private IMessageInfoService messageInfoService;

    /**
     * 支付
     * @param orderId   订单ID
     * @param payChannel    渠道
     * @return  ResponseResult
     */
    @RequestMapping("/pay")
    public ResponseResult<Object> pay(Integer orderId, Integer payChannel, BigDecimal payPrice){
        ResponseResult.ResponseResultBuilder<Object> builder = ResponseResult.builder();
        //1.预发布消息
        ResponseResult<Object> messageResult = messageInfoService.beforehandMessage(orderId+"");
        if(!messageResult.getCode().equals(CommonConstants.CommonResponseEnum.SUCCESS.getCode())){
            return builder.commonFail(CommonConstants.CommonResponseEnum.FAIL).build();
        }

        //2.处理业务逻辑
        PayInfo payInfo = new PayInfo();
        payInfo.setOrderId(orderId);
        payInfo.setPayChannel(payChannel);
        payInfo.setPayPrice(payPrice);
        //封装发送结果消息实体
        ResponseResult.ResponseResultBuilder<SendMessageResult<Object>> sendProcessResult = ResponseResult.builder();
        SendMessageResult<Object> sendMessageResult = new SendMessageResult<>();
        //唯一ID  保证一个订单只处理一次  幂等性
        sendMessageResult.setUniqueId(orderId+"");
        //支付信息
        sendMessageResult.setData(payInfo);
        if (payInfo.insert()) {
            //处理成功
            sendProcessResult = sendProcessResult.commonSuccess(CommonConstants.CommonResponseEnum.SUCCESS,sendMessageResult);
        }else {
            //处理失败
            sendProcessResult = sendProcessResult.commonFail(CommonConstants.CommonResponseEnum.SUCCESS,sendMessageResult);
        }
        //3.发送消息处理结果
        messageInfoService.sendProcessResult(sendProcessResult.build());

        return builder.commonSuccess(CommonConstants.CommonResponseEnum.SUCCESS,payInfo).build();
    }

}
