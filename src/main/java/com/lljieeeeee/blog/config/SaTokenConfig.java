package com.lljieeeeee.blog.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.lljieeeeee.blog.utils.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

/**
 * @author Lljieeeeee
 * @date 2021/9/21 11:02
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    /**
     * 注册sa-token拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaRouteInterceptor((req, resp, handler) -> {
            // 获取配置文件中的白名单路径
            List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
            // 登录认证：除白名单路径外均需要登录认证
            SaRouter.match(Collections.singletonList("/**"), ignoreUrls, StpUtil::checkLogin);
        })).addPathPatterns("/**");
    }

    /**
     * 注册 [sa-token全局过滤器]
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**").addExclude("/favicon.ico")
                .setAuth(r -> {
                    SaRouter.match(Collections.singletonList("/**"), ignoreUrlsConfig.getUrls(), StpUtil::checkLogin);
                })
                .setError(e -> {
                    return R.error().message(e.getMessage());
                })
                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(r -> {
                    // ---------- 设置跨域响应头 ----------
                    SaHolder.getResponse()
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", "*")
                            // 允许所有请求方式
                            .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                            // 允许的header参数
                            .setHeader("Access-Control-Allow-Headers", "x-requested-with,satoken,authorization,content-type");

                    // 如果是预检请求，直接返回
                    if ("OPTIONS".equals(SaHolder.getRequest().getMethod())) {
                        SaRouter.back();
                    }
                });
    }
}
