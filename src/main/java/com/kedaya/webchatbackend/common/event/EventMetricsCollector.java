package com.kedaya.webchatbackend.common.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.common.event
 * @Project：webchat-backend
 * @name：EventMetricsCollector
 * @Date：2025-07-28 00:37
 * @Filename：EventMetricsCollector
 * 事件指标收集器
 * 用于记录事件发布和处理的指标
 */
@Slf4j
@Component
public class EventMetricsCollector {

    public void incrementPublishedEvents(String eventType) {
        log.info("事件发布成功: type={}", eventType);

    }

    public void incrementFailedEvents(String eventType) {
        log.error("事件发布失败: type={}", eventType);
    }
}
