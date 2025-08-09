package com.kedaya.webchatbackend.common.event;

import com.kedaya.webchatbackend.exception.EventPublishException;
import com.kedaya.webchatbackend.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.common.event
 * @Project：webchat-backend
 * @name：EventPublisher
 * @Date：2025-07-28 00:27
 * @Filename：EventPublisher 统一事件发布器 - 对Spring的ApplicationEventPublisher进行封装
 * 作用：
 * 1. 提供统一的事件发布接口
 * 2. 增加事件发布的日志记录
 * 3. 支持事件发布的监控和统计
 * 4. 可以添加事件发布前后的钩子处理
 * 5. 支持条件发布和批量发布
 */
@Slf4j
@Component
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private EventMetricsCollector metricsCollector; // 事件指标收集器

    /**
     * 发布事件
     */
    public void publishEvent(BaseEvent event) {
        try {
            // 发布前处理
            beforePublish(event);

            // 发布事件
            log.info("发布事件: {}", JsonUtils.toJson(event));
            applicationEventPublisher.publishEvent(event);

            // 发布后处理
            afterPublish(event);

            // 记录指标
            metricsCollector.incrementPublishedEvents(event.getEventType());

        } catch (Exception e) {
            log.error("事件发布失败: {}", event, e);
            metricsCollector.incrementFailedEvents(event.getEventType());
            throw new EventPublishException(e.getMessage(), e.getCause());
        }
    }

    /**
     * 条件发布事件
     */
    public void publishEventIf(BaseEvent event, boolean condition) {
        if (condition) {
            publishEvent(event);
        } else {
            log.debug("条件不满足，跳过事件发布: {}", event);
        }
    }

    /**
     * 批量发布事件
     */
    public void publishEvents(BaseEvent... events) {
        for (BaseEvent event : events) {
            publishEvent(event);
        }
    }

    /**
     * 异步发布事件（返回Future）
     */
    @Async("eventExecutor")
    public CompletableFuture<Void> publishEventAsync(BaseEvent event) {
        publishEvent(event);
        return CompletableFuture.completedFuture(null);
    }

    /**
     * 发布前钩子
     */
    private void beforePublish(BaseEvent event) {
        // 设置追踪ID
        if (event.getTraceId() == null) {
            event.setTraceId(getCurrentTraceId());
        }

        // 记录发布日志
        log.info("准备发布事件: type={}, id={}, source={}",
                event.getEventType(), event.getEventId(), event.getSourceService());
    }

    /**
     * 发布后钩子
     */
    private void afterPublish(BaseEvent event) {
        log.info("事件发布成功: type={}, id={}", event.getEventType(), event.getEventId());

        // 可以在这里添加其他后处理逻辑
        // 比如：保存事件到事件存储、发送到消息队列等
    }

    private String getCurrentTraceId() {
        // 从MDC、Spring Cloud Sleuth或其他追踪框架获取
        return MDC.get("traceId");
    }
}
