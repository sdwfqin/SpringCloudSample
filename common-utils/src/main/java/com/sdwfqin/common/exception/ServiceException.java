package com.sdwfqin.common.exception;

import com.sdwfqin.common.result.ResultEnum;

/**
 * 自定义异常，必须继承RuntimeException
 */
public class ServiceException extends RuntimeException {

    private Integer code;

    public ServiceException(ResultEnum resultErrorEnum) {
        super(resultErrorEnum.getMsg());
        this.code = resultErrorEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
