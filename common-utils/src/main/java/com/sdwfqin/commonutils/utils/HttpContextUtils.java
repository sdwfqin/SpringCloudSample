package com.sdwfqin.commonutils.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class HttpContextUtils {
    private static String XHR = "XMLHttpRequest";
    private static String UNKNOWN = "unknown";
    private static String X_REQUESTED_WITH = "X-Requested-With";
    private static List<String> IP_HEADERS = Arrays.asList("X-Real-IP", "X-Forwarded-For", "Proxy-Client-IP",
            "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR");

    /**
     * 获取Request
     */
    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequest = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequest != null ? servletRequest.getRequest() : null;
    }

    /**
     * 判断是否为ajax请求
     */
    public static boolean isAjax(HttpServletRequest req) {
        //判断是否为ajax请求，默认不是
        return XHR.equalsIgnoreCase(req.getHeader(X_REQUESTED_WITH));
    }

    /**
     * 获取IP地址
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        for (String header : IP_HEADERS) {
            String ip = request.getHeader(header);
            if (!StringUtils.isEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
}
