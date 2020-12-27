package com.sdwfqin.cloudsample.apizuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证过滤器
 * <p>
 *
 * @author 张钦
 * @date 2019/11/14
 */
public class AuthorizationFilter extends ZuulFilter {

    /**
     * Pre 优先 Post执行，此时filterOrder没有作用。
     * filterType相同参照filterOrder
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 数字越小优先级越高
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * true执行run方法
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        return null;
    }
}
