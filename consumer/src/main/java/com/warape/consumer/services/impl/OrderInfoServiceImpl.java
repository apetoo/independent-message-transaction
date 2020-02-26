package com.warape.consumer.services.impl;

import com.warape.consumer.entity.OrderInfo;
import com.warape.consumer.mapper.OrderInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.warape.consumer.services.IOrderInfoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

}
