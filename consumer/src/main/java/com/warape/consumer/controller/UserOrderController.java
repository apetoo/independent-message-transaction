package com.warape.consumer.controller;


import cn.hutool.http.HttpStatus;
import com.warape.consumer.entity.OrderInfo;
import com.warape.consumer.services.IOrderInfoService;
import com.warape.consumer.utlis.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanmingyu
 * @since 2020-02-19
 */
@Controller
@RequestMapping("/userOrder")
public class UserOrderController {

    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 创建订单
     * @param price
     * @param productName
     * @param userId
     * @return
     */
    @RequestMapping("/createOrder")
    public ResponseResult<Object> createOrder(BigDecimal price,String productName,Integer userId){
        ResponseResult.ResponseResultBuilder<Object> builder = ResponseResult.builder();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPrice(price);
        orderInfo.setProductName(productName);
        //待支付
        orderInfo.setState(1);
        orderInfo.setUserId(userId);
        if(orderInfoService.save(orderInfo)){
            return builder.code(HttpStatus.HTTP_OK).msg("HTTP_OK").build();
        }else {
            return builder.code(HttpStatus.HTTP_BAD_REQUEST).msg("HTTP_BAD_REQUEST").build();
        }
    }


}

