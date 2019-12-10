package com.sdwfqin.common.result;

import lombok.Data;

/**
 * Result Base
 * <p>
 *
 * @author 张钦
 * @date 2019/8/30
 */
@Data
public class Result<T> {

    /**
     * 状态值
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
