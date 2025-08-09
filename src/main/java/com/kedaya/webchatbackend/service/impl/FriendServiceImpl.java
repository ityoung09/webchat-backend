package com.kedaya.webchatbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kedaya.webchatbackend.common.event.EventPublisher;
import com.kedaya.webchatbackend.event.domain.UserRegisteredEvent;
import com.kedaya.webchatbackend.model.entity.FriendInfo;
import com.kedaya.webchatbackend.repository.FriendRepository;
import com.kedaya.webchatbackend.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.service.impl
 * @Project：webchat-backend
 * @name：FriendServiceImpl
 * @Date：2025-07-28 20:30
 * @Filename：FriendServiceImpl
 */
@Slf4j
@Service
public class FriendServiceImpl extends ServiceImpl<FriendRepository, FriendInfo> implements FriendService {

    @Autowired
    private EventPublisher eventPublisher;

    public void testEvent() {

    }
}
