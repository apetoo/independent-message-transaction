package com.warape.messagecenter.services;


import com.baomidou.mybatisplus.extension.service.IService;
import com.warape.messagecenter.entity.MessageInfo;
import org.warape.commons.dto.SendMessageResult;
import org.warape.commons.repsonse.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanmingyu
 * @since 2020-02-21
 */
public interface IMessageInfoService extends IService<MessageInfo> {

    /**
     * 预发送消息
     * @param result
     * @return
     */
    ResponseResult<Object> beforehandMessage(String result);

    /**
     * 发送处理结果
     * @param responseResult
     */
    void sendProcessResult(ResponseResult<SendMessageResult<Object>> responseResult);
}
