package com.kedaya.webchatbackend.event.listener;

import com.kedaya.webchatbackend.event.domain.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.event.listener
 * @Project：webchat-backend
 * @name：UserRegisteredListener
 * @Date：2025-07-28 20:41
 * @Filename：UserRegisteredListener
 */
@Component
public class UserRegisteredListener {

    @EventListener
    public void handleUserRegisteredSync(UserRegisteredEvent event) {
        System.out.println("【同步处理】收到用户注册事件: " + event.getUserId());
        // 模拟同步处理逻辑
        try {
            Thread.sleep(5000); // 模拟耗时操作
            System.out.println("【同步处理】用户权限初始化完成: " + event.getUserId());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
