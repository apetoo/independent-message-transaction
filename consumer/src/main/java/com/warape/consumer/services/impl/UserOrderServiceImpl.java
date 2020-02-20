package com.warape.consumer.services.impl;

import com.warape.consumer.entity.UserOrder;
import com.warape.consumer.mapper.UserOrderMapper;
import com.warape.consumer.services.IUserOrderService;
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
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements IUserOrderService {

}
