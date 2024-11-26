package com.gdou.common;

/**
 * @author howe
 */
public class ExcludePathCommon {
    /**
     * 放行路径
     */
    public static final String[] EXCLUDE_PATH_PATTERNS = {
            // Swagger
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/v3/**",
            "/swagger-ui.html/**",
            "/doc.html/**",
            "/error",
            "/favicon.ico",
            // 请求路径
            "/user/login",
            "/user/logout"

    };

    public static final String[] WEBLOG_EXCLUDE = {
            "login",
            "logout",
            "register",
            "code",
            "activeCheckNotice",
            "active",
            "submitTaskByFace",
            "alipay",
            "order/notify"
    };
}
