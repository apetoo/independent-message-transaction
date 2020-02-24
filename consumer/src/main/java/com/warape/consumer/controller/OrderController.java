package com.warape.consumer.controller;

import com.warape.consumer.entity.OrderInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.warape.commons.constants.CommonConstants;
import org.warape.commons.constants.OrderConstants;
import org.warape.commons.repsonse.ResponseResult;

import java.math.BigDecimal;

/**
 * @author wanmingyu
 */
@RestController
public class OrderController {

    /**
     * 创建订单
     * @param userId
     * @param price
     * @param productName
     * @return
     */
    @RequestMapping("/createOrder")
    public ResponseResult<Object> createOrder(@RequestParam Integer userId, @RequestParam BigDecimal price, @RequestParam String productName){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setProductName(productName);
        orderInfo.setPrice(price);
        orderInfo.setState(OrderConstants.OderStateEnum.NOT_PAYMENT.getState());
        if (orderInfo.insert()) {
            return ResponseResult.builder().commonSuccess(CommonConstants.CommonResponseEnum.SUCCESS,orderInfo).build();
        }else {
            return ResponseResult.builder().commonSuccess(CommonConstants.CommonResponseEnum.FAIL,null).build();
        }
    }
}
