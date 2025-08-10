package com.kedaya.webchatbackend.utils;

import cn.dev33.satoken.stp.StpUtil;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.utils
 * @Project：webchat-backend
 * @name：UserUtils
 * @Date：2025-08-10 13:36
 * @Filename：UserUtils
 */
public class UserUtils {

    public static Long getCurrentUserId() {
        return Long.valueOf(StpUtil.getLoginId().toString());
    }
}
