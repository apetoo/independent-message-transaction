package com.warape.consumer.controller;


import cn.hutool.http.HttpStatus;
import com.warape.consumer.entity.PayInfo;
import com.warape.consumer.services.IPayInfoService;
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
@RequestMapping("/payInfo")
public class PayInfoController {

    @Autowired
    private IPayInfoService payInfoService;

    @RequestMapping("/pay")
    public ResponseResult<Object> pay(Integer orderId, Integer payChannel, BigDecimal price){
        PayInfo payInfo = new PayInfo();
        payInfo.setOrderId(orderId);
        payInfo.setPayChannel(payChannel);
        payInfo.setPayPrice(price);
        if(payInfoService.pay(payInfo)){
            return ResponseResult.builder().code(HttpStatus.HTTP_OK).msg("HTTP_OK").build();
        }else {
            return ResponseResult.builder().code(HttpStatus.HTTP_BAD_REQUEST).msg("HTTP_BAD_REQUEST").build();
        }
    }
}

