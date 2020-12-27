package com.sdwfqin.cloudsample.common.exception;

import com.sdwfqin.cloudsample.common.result.ResultEnum;

/**
 * 自定义异常，必须继承RuntimeException
 */
public class ServiceException extends RuntimeException {

    private Integer code;

    public ServiceException(ResultEnum resultErrorEnum) {
        super(resultErrorEnum.getMsg());
        this.code = resultErrorEnum.getCode();
    }

    public ServiceException(String message) {
        super(message);
        this.code = 500;
    }

    public ServiceException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
