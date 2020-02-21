package org.warape.commons.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wanmingyu
 */
public interface OrderConstants extends CommonConstants {

    @Getter
    @AllArgsConstructor
    enum ResponseEnum implements OrderConstants {

        /**
         * 订单不存在
         */
        NOT_FOUND_ORDER(4001, "不存在该订单"),
        ;

        private Integer code;
        private String msg;

    }


    @Getter
    @AllArgsConstructor
    enum OderStateEnum implements OrderConstants {
        /**
         * 未付款
         */
        NOT_PAYMENT(1),
        /**
         * 已付款
         */
        YES_PAYMENT(2),
        ;
        private Integer state;

    }
}
