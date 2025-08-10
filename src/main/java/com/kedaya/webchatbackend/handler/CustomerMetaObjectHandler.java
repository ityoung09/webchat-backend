package com.kedaya.webchatbackend.handler;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.kedaya.webchatbackend.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.handler
 * @Project：webchat-backend
 * @name：MetaObjectHandler
 * @Date：2025-07-16 21:33
 * @Filename：MetaObjectHandler
 */
@Slf4j
@Component
public class CustomerMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Long loginId = StpUtil.isLogin() ? UserUtils.getCurrentUserId() : -1L;
        this.strictInsertFill(metaObject, "createdBy", Long.class, loginId);
        this.strictInsertFill(metaObject, "createdDate", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "isDeleted", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long loginId = StpUtil.isLogin() ? UserUtils.getCurrentUserId() : -1L;
        this.strictUpdateFill(metaObject, "updatedBy", Long.class, loginId);
        this.strictUpdateFill(metaObject, "updatedDate", LocalDateTime.class, LocalDateTime.now());
    }
}
