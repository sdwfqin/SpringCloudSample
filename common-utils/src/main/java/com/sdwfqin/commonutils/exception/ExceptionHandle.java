package com.sdwfqin.commonutils.exception;

import com.sdwfqin.commonutils.result.Result;
import com.sdwfqin.commonutils.result.ResultEnum;
import com.sdwfqin.commonutils.result.ResultUtils;
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
    public Result<String> handle(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            return ResultUtils.errorData(serviceException.getCode(), serviceException.getMessage());
        } else if (e instanceof ConstraintViolationException
                || e instanceof MissingServletRequestParameterException) {
            return ResultUtils.errorData(ResultEnum.VALID_ERROR);
        }
        log.error("【系统异常】{}", e);
        return ResultUtils.errorData(ResultEnum.ERROR_UNKONE);
    }
}
