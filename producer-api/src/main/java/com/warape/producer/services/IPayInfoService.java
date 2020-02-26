package com.warape.producer.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.warape.producer.entity.PayInfo;
import org.warape.commons.repsonse.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanmingyu
 * @since 2020-02-21
 */
public interface IPayInfoService extends IService<PayInfo> {

    /**
     * 支付系统业务处理状态确认
     * @param orderId
     * @return
     */
    boolean confirmPayState(Integer orderId);
}
