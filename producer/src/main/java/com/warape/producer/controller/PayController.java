package com.warape.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.warape.commons.repsonse.ResponseResult;

/**
 * @author wanmingyu
 */
@RestController
public class PayController {

    /**
     * 支付
     * @param orderId   订单ID
     * @param payChannel    渠道
     * @return  ResponseResult
     */
    @RequestMapping("/pay")
    public ResponseResult<Object> pay(Integer orderId,String payChannel){
        return null;
    }
}
