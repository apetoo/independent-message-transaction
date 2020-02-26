package org.warape.commons.repsonse;

import lombok.ToString;
import org.warape.commons.constants.CommonConstants;

import java.io.Serializable;

/**
 * @program: sugar
 * @description: 统一响应
 * @author: 万明宇 (warApe)
 * @create: 2019-06-19 13:17
 **/
@ToString
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -1;
    private Integer code;
    private String msg;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult() {
    }

    public static <T> ResponseResult.ResponseResultBuilder<T> builder() {
        return new ResponseResult.ResponseResultBuilder();
    }

    public static class ResponseResultBuilder<T> {

        private String msg;
        private Integer code;
        private T data;

        ResponseResultBuilder() {
        }

        public ResponseResult.ResponseResultBuilder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public ResponseResult.ResponseResultBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ResponseResult.ResponseResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        /**
         * 成功
         *
         * @param commonConstants
         * @param data
         * @return
         */
        public ResponseResult.ResponseResultBuilder<T> commonSuccess(CommonConstants commonConstants, T data) {
            Integer code = commonConstants.getCode();
            String msg = commonConstants.getMsg();
            this.code = code;
            this.msg = msg;
            this.data = data;
            return this;
        }

        /**
         * 失败
         *
         * @param commonConstants
         * @return
         */
        public ResponseResult.ResponseResultBuilder<T> commonFail(CommonConstants commonConstants) {
            Integer code = commonConstants.getCode();
            String msg = commonConstants.getMsg();
            this.code = code;
            this.msg = msg;
            return this;
        }

        /**
         * 失败
         *
         * @param commonConstants
         * @return
         */
        public ResponseResult.ResponseResultBuilder<T> commonFail(CommonConstants commonConstants,T data) {
            Integer code = commonConstants.getCode();
            String msg = commonConstants.getMsg();
            this.code = code;
            this.msg = msg;
            this.data = data;
            return this;
        }

        public ResponseResult<T> build() {
            return new ResponseResult(this.code, this.msg, this.data);
        }
    }
}
