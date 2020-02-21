package org.warape.commons.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wanmingyu
 */
public interface CommonConstants {

    @Getter
    @AllArgsConstructor
    enum CommonResponseEnum implements CommonConstants {
        /**
         * 成功
         */
        SUCCESS(200, "成功"),
        /**
         * 失败
         */
        FAIL(400, "失败"),
        ;
        private Integer code;
        private String msg;

    }

    default Integer getCode(){
        return null;
    }

    default String getMsg(){
        return null;
    }
}
