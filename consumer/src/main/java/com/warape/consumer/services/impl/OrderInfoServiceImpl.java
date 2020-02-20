package com.warape.consumer.services.impl;

import com.warape.consumer.entity.OrderInfo;
import com.warape.consumer.mapper.OrderInfoMapper;
import com.warape.consumer.services.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

}
