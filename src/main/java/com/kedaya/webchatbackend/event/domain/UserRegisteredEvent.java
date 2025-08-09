package com.kedaya.webchatbackend.event.domain;

import com.kedaya.webchatbackend.common.event.BaseEvent;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.event.domain
 * @Project：webchat-backend
 * @name：UserRegisteredEvent
 * @Date：2025-07-28 20:38
 * @Filename：UserRegisteredEvent
 */
@Getter
public class UserRegisteredEvent extends BaseEvent {

    private final String userId;
    private final String email;
    private final LocalDateTime registeredTime;

    public UserRegisteredEvent(Object source, String userId, String email, String eventType) {
        super(source, eventType);
        this.userId = userId;
        this.email = email;
        this.registeredTime = LocalDateTime.now();
    }

}
