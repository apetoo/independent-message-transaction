package com.warape.producer.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.warape.producer.entity.PayInfo;
import com.warape.producer.mapper.PayInfoMapper;
import com.warape.producer.services.IPayInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;
import org.warape.commons.constants.PayConstants;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanmingyu
 * @since 2020-02-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PayInfoServiceImpl extends ServiceImpl<PayInfoMapper, PayInfo> implements IPayInfoService {

    @Override
    public boolean confirmPayState(Integer orderId) {

        LambdaQueryWrapper<PayInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PayInfo::getOrderId,orderId);

        PayInfo payInfo = getOne(queryWrapper);
        Integer payState = payInfo.getPayState();
        return PayConstants.PayState.SUCCESS.getState().equals(payState);
    }
}
