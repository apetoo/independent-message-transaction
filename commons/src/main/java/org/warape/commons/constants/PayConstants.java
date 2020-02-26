package org.warape.commons.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wanmingyu
 */
public interface PayConstants {

    @Getter
    @AllArgsConstructor
    enum PayState{
        /**
         * 支付状态
         */
        FAIL(0),
        SUCCESS(1),
        ;

        private Integer state;
    }
}
