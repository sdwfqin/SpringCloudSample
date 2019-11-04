package com.sdwfqin.commonutils.result;

/**
 * Result响应工具类
 * <p>
 *
 * @author 张钦
 * @date 2019/8/30
 */
public class ResultUtils {

    public static <T> Result<T> resultData(ResultEnum resultEnum, T t) {
        return resultData(resultEnum.getCode(), resultEnum.getMsg(), t);
    }

    public static <T> Result<T> errorData(ResultEnum resultEnum) {
        return resultData(resultEnum.getCode(), resultEnum.getMsg(), null);
    }

    public static <T> Result<T> errorData(Integer code, String msg) {
        return resultData(code, msg, null);
    }

    private static <T> Result<T> resultData(Integer code, String msg, T t) {

        Result<T> result = new Result<>();

        result.setCode(code);
        result.setMsg(msg);
        result.setData(t);

        return result;
    }
}
