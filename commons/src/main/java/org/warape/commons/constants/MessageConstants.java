package org.warape.commons.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wanmingyu
 */
public interface MessageConstants extends CommonConstants {

    @Getter
    @AllArgsConstructor
    enum ResponseEnum implements MessageConstants {

        /**
         * 消息存储失败
         */
        MESSAGE_ERROR(4001, "消息存储失败"),
        ;

        private Integer code;
        private String msg;

    }


    @Getter
    @AllArgsConstructor
    enum MessageStateEnum implements MessageConstants {
        /**
         * '状态 0:发送失败 1:已发送  2:待发送 3:可发送'
         */
        SEND_ERROR(0),
        SEND_SUCCESS(1),
        WAIT_SEND(2),
        BE_SEND(3);
        private Integer state;

    }
}
