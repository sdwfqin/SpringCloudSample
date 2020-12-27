package com.sdwfqin.cloudsample.common.utils;

import com.sdwfqin.cloudsample.common.entity.AuthUserDO;
import com.sdwfqin.cloudsample.common.entity.RequestMetaData;
import com.sdwfqin.cloudsample.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class ContextUtils {
    private static final ThreadLocal<RequestMetaData> metaHolder = new ThreadLocal<>();

    public static AuthUserDO getUserData() {
        return (AuthUserDO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getUserID() {
        return getUserData().getId();
    }

    public static String getUsername() {
        return getUserData().getUsername();
    }

    public static String getToken() {
        checkRequestMetaData();
        return metaHolder.get().getToken();
    }

    public static void setToken(String token) {
        checkRequestMetaData();
        metaHolder.get().setToken(token);
    }

    public static String getAppId() {
        checkRequestMetaData();
        String appId = metaHolder.get().getAppId();
        if (StringUtils.isEmpty(appId)){
            throw new ServiceException("appId不能为空");
        }
        return appId;
    }

    public static void setAppId(String appId) {
        checkRequestMetaData();
        metaHolder.get().setAppId(appId);
    }

    public static void remove() {
        metaHolder.remove();
    }

    private static void checkRequestMetaData(){
        if (metaHolder.get() == null){
            metaHolder.set(new RequestMetaData());
        }
    }
}
