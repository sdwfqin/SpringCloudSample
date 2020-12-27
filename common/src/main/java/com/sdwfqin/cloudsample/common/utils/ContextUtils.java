package com.sdwfqin.cloudsample.common.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.sdwfqin.cloudsample.common.entity.AuthUserDO;
import com.sdwfqin.cloudsample.common.entity.RequestMetaData;
import com.sdwfqin.cloudsample.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class ContextUtils {
    private static final ThreadLocal<RequestMetaData> metaHolder = new ThreadLocal<>();

    public static AuthUserDO getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // TODO 在非认证服务getPrincipal只能获取到Username，暂时这样处理
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
            Map<String, Object> userAuthenticationDetailMap = (LinkedHashMap<String, Object>) userAuthentication.getDetails();

            String principalJsonString = JSONUtils.toJSONString(userAuthenticationDetailMap.get("principal"));

            return JSONUtil.toBean(principalJsonString, AuthUserDO.class);
        }
        return (AuthUserDO) authentication.getPrincipal();
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
        if (StringUtils.isEmpty(appId)) {
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

    private static void checkRequestMetaData() {
        if (metaHolder.get() == null) {
            metaHolder.set(new RequestMetaData());
        }
    }
}
