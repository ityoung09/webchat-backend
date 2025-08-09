package com.kedaya.webchatbackend.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.config
 * @Project：webchat-backend
 * @name：SaTokenConfig
 * @Date：2025-07-19 12:40
 * @Filename：SaTokenConfig
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> {
                    SaRouter.match(SaHttpMethod.OPTIONS).free(r -> System.out.println("预请求直接放行")).stop();
                    SaRouter.match("/**")
                            .notMatch("/user/login", "/user/register")
                            .notMatch("/doc.html",              // Knife4j UI
                                    "/webjars/**",
                                    "/v3/api-docs/**",
                                    "/swagger-resources/**",
                                    "/swagger-ui.html",
                                    "/swagger-ui/**",
                                    "/favicon.ico")
                            .check(r -> StpUtil.checkLogin());
                }))
                .addPathPatterns("/**");
    }
}
