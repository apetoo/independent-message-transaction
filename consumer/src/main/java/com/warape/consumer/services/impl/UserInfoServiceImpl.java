package com.warape.consumer.services.impl;

import com.warape.consumer.entity.UserInfo;
import com.warape.consumer.mapper.UserInfoMapper;
import com.warape.consumer.services.IUserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
