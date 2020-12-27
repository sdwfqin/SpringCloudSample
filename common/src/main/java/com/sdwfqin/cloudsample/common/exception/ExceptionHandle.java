package com.sdwfqin.cloudsample.common.exception;

import com.sdwfqin.cloudsample.common.result.Result;
import com.sdwfqin.cloudsample.common.result.ResultEnum;
import com.sdwfqin.cloudsample.common.result.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            return ResultUtils.errorData(serviceException.getCode(), serviceException.getMessage());
        } else if (e instanceof ConstraintViolationException
                || e instanceof MissingServletRequestParameterException) {
            return ResultUtils.errorData(ResultEnum.VALID_ERROR.getCode(), ResultEnum.VALID_ERROR.getMsg());
        }
        log.error("【系统异常】", e);
        return ResultUtils.errorData(ResultEnum.ERROR_UNKONE.getCode(), ResultEnum.ERROR_UNKONE.getMsg());
    }
}
