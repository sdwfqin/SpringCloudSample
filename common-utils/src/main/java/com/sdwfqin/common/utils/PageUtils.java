package com.sdwfqin.common.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author bootdo 1992lcg@163.com
 */
@Data
public class PageUtils implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int total;
    private List<?> datas;

    public PageUtils(List<?> list, int total) {
        this.datas = list;
        this.total = total;
    }

}
