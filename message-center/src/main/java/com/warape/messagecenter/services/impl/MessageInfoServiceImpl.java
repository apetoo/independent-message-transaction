package com.warape.messagecenter.services.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.warape.messagecenter.entity.MessageInfo;
import com.warape.messagecenter.mapper.MessageInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanmingyu
 * @since 2020-02-21
 */
@Service
public class MessageInfoServiceImpl extends ServiceImpl<MessageInfoMapper, MessageInfo> implements IService<MessageInfo> {

}
