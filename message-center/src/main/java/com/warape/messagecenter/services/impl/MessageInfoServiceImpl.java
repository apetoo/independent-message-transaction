package com.warape.messagecenter.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.warape.messagecenter.entity.MessageInfo;
import com.warape.messagecenter.mapper.MessageInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.warape.messagecenter.services.IMessageInfoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.warape.commons.constants.CommonConstants;
import org.warape.commons.constants.MessageConstants;
import org.warape.commons.dto.SendMessageResult;
import org.warape.commons.repsonse.ResponseResult;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanmingyu
 * @since 2020-02-21
 */
@Service
public class MessageInfoServiceImpl extends ServiceImpl<MessageInfoMapper, MessageInfo> implements IMessageInfoService {

    @Override
    public ResponseResult<Object> beforehandMessage(String messageId) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setMessageId(messageId);
        messageInfo.setState(MessageConstants.MessageStateEnum.WAIT_SEND.getState());
        if (messageInfo.insert()) {
            return ResponseResult.builder().commonSuccess(CommonConstants.CommonResponseEnum.SUCCESS, null).build();
        }else {
            return ResponseResult.builder().commonFail(CommonConstants.CommonResponseEnum.FAIL).build();
        }
    }

    @Override
    public void sendProcessResult(ResponseResult<SendMessageResult<Object>> responseResult) {
        LambdaUpdateWrapper<MessageInfo> updateWrapper = new LambdaUpdateWrapper<>();

        SendMessageResult<Object> data = responseResult.getData();
        String json = JSON.toJSONString(data);
        if(CommonConstants.CommonResponseEnum.SUCCESS.getCode().equals(responseResult.getCode())){
            //成功 设置为待发送
            updateWrapper.eq(MessageInfo::getMessageId, data.getUniqueId())
                    .set(MessageInfo::getState, MessageConstants.MessageStateEnum.WAIT_SEND.getState())
                    .set(MessageInfo::getHandlerJson,json);
        }else{
            //失败 设置为失败
            updateWrapper.eq(MessageInfo::getMessageId, data.getUniqueId())
                    .set(MessageInfo::getState, MessageConstants.MessageStateEnum.SEND_ERROR.getState())
                    .set(MessageInfo::getHandlerJson, json);
        }
        //更新操作
        if (!update(updateWrapper)) {
            //TODO 更新失败  加入定时器扫描或者人工处理
        }
    }
}
