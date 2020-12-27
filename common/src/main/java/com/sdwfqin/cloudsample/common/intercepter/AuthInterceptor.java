package com.sdwfqin.cloudsample.common.intercepter;

import com.sdwfqin.cloudsample.common.constants.CommonConstants;
import com.sdwfqin.cloudsample.common.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器
 * <p>
 *
 * @author 张钦
 * @date 2020/12/15
 */
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        String appId = request.getHeader(CommonConstants.CONTEXT_APP_ID);
        ContextUtils.setAppId(appId);
        if (StringUtils.isNoneEmpty(token)) {
            ContextUtils.setToken(token);
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContextUtils.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}