package com.warape.consumer.services;

import com.warape.consumer.entity.PayInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanmingyu
 * @since 2020-02-19
 */
public interface IPayInfoService extends IService<PayInfo> {

    boolean pay(PayInfo payInfo);
}
