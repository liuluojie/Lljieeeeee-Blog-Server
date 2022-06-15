package com.lljieeeeee.blog.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuluojie
 * @date 2022/6/12 13:24
 */
@Slf4j
@Aspect
@Order(5)
@Component
public class RequestAspect {

    @Pointcut("execution(public * com.lljieeeeee.blog.controller..*.*(..))")
    public void controllerLog() {
    }

    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String method = request.getMethod();
        log.info("请求方式 --->> {}", method);
        log.info("请求URL --->> {}", request.getRequestURL());
        log.info("请求IP  --->> {}", request.getRemoteAddr());
        log.info("请求方法 --->> {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        if(RequestMethod.POST.name().equals(method) || RequestMethod.PUT.name().equals(method)){
            // 记录下请求内容
            // 获取参数, 只取自定义的参数, 自带的HttpServletRequest, HttpServletResponse不管
            if (joinPoint.getArgs().length > 0) {
                for (Object o : joinPoint.getArgs()) {
                    if (o instanceof HttpServletRequest || o instanceof HttpServletResponse) {
                        continue;
                    }
                    log.info("请求参数 --->> {}", JSON.toJSONString(o));
                }
            }
        }else {
            Map<String, Object> bizDataMap = new HashMap<>();
            Enumeration<String> e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                bizDataMap.put(name, request.getParameter(name));
            }
            // 请求信息
            log.info("请求参数 --->> params:{}", JSON.toJSONString(bizDataMap));

        }
    }

    @AfterReturning(returning = "result", pointcut = "controllerLog()")
    public void doAfterReturning(Object result) throws Throwable {
        // 处理完请求，返回内容
        log.info("响应数据 --->> {}", JSON.toJSONString(result));
    }
}