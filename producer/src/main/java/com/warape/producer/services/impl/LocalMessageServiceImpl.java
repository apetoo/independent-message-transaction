package com.warape.producer.services.impl;

import com.warape.producer.entity.LocalMessage;
import com.warape.producer.mapper.LocalMessageMapper;
import com.warape.producer.services.ILocalMessageService;
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
public class LocalMessageServiceImpl extends ServiceImpl<LocalMessageMapper, LocalMessage> implements ILocalMessageService {

}
