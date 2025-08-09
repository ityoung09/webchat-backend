package com.kedaya.webchatbackend;

import com.kedaya.webchatbackend.common.event.EventPublisher;
import com.kedaya.webchatbackend.event.domain.UserRegisteredEvent;
import com.kedaya.webchatbackend.service.impl.FriendServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebchatBackendApplicationTests {

    @Autowired
    private EventPublisher eventPublisher;

    @Test
    void contextLoads() {
        // 使用简单的字符串作为source，避免序列化复杂对象
        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(this, "1", "1", "1");
        System.out.println(userRegisteredEvent.getMetadata());
        eventPublisher.publishEvent(userRegisteredEvent);
    }

}
