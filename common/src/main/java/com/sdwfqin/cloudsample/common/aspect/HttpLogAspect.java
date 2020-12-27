package com.sdwfqin.cloudsample.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 请求日志
 * <p>
 *
 * @author 张钦
 * @date 2019/9/9
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class HttpLogAspect {

    /**
     * 拦截这个类的所有方法
     */
    @Pointcut("execution(public * com.sdwfqin.*.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 请求内容
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("请求地址 : {}", request.getRequestURL().toString());
        log.info("HTTP METHOD : {}", request.getMethod());
        log.info("CLASS_METHOD : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("参数 : {}", Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 处理完请求，返回内容
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {

        // 处理完请求，返回内容
        log.info("返回值 : {}", ret.toString());
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        log.info("耗时 : {}", System.currentTimeMillis() - startTime);
        return ob;
    }
}

