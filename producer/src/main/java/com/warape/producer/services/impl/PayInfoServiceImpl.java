package com.warape.producer.services.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.warape.producer.entity.OrderInfo;
import com.warape.producer.entity.PayInfo;
import com.warape.producer.mapper.OrderInfoMapper;
import com.warape.producer.mapper.PayInfoMapper;
import com.warape.producer.services.IPayInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanmingyu
 * @since 2020-02-19
 */
@Service
public class PayInfoServiceImpl extends ServiceImpl<PayInfoMapper, PayInfo> implements IPayInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public boolean pay(PayInfo payInfo) {

        boolean save = save(payInfo);
        if(save){
            //保存支付记录成功
            OrderInfo entity = new OrderInfo();
            //交易成功
            entity.setState(2);
            LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<OrderInfo>().eq(OrderInfo::getId, payInfo.getOrderId());
            boolean falg = SqlHelper.retBool(orderInfoMapper.update(entity, updateWrapper));

        }

        return false;
    }
}
