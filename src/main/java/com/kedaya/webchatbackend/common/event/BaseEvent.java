package com.kedaya.webchatbackend.common.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.common.event
 * @Project：webchat-backend
 * @name：BaseEvent
 * @Date：2025-07-28 00:27
 * @Filename：BaseEvent
 * 事件基类 - 提供所有业务事件的通用属性和行为
 * 作用：
 * 1. 统一事件的基本属性（事件ID、时间戳、来源等）
 * 2. 提供事件的通用方法
 * 3. 便于事件的统一管理和监控
 * 4. 支持事件的序列化和反序列化
 */
public abstract class BaseEvent extends ApplicationEvent {

    // 事件唯一标识
    private final String eventId;

    // 事件发生时间
    private final LocalDateTime localDateTime;

    // 事件类型（用于分类和路由）
    private final String eventType;

    // 事件版本（用于事件演进和兼容性）
    private final String version;

    // 事件来源服务（微服务环境下很有用）
    private final String sourceService;

    // 追踪ID（用于分布式链路追踪）
    private String traceId;

    protected BaseEvent(Object source, String eventType) {
        super(source);
        this.eventId = UUID.randomUUID().toString();
        this.localDateTime = LocalDateTime.now();
        this.eventType = eventType;
        this.version = "1.0";
        this.sourceService = determineSourceService();
    }

    protected BaseEvent(Object source, String eventType, String version) {
        super(source);
        this.eventId = UUID.randomUUID().toString();
        this.localDateTime = LocalDateTime.now();
        this.eventType = eventType;
        this.version = version;
        this.sourceService = determineSourceService();
    }

    // 确定事件来源服务
    private String determineSourceService() {
        // 可以从配置文件、环境变量或者上下文中获取
        return System.getProperty("spring.application.name", "unknown-service");
    }

    // Getters
    public String getEventId() { return eventId; }
    public LocalDateTime getLocalDateTime() { return localDateTime; }
    public String getEventType() { return eventType; }
    public String getVersion() { return version; }
    public String getSourceService() { return sourceService; }
    public String getTraceId() { return traceId; }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    // 事件元数据
    public EventMetaData getMetadata() {
        return EventMetaData.builder()
                .eventId(eventId)
                .eventType(eventType)
                .timestamp(localDateTime)
                .version(version)
                .sourceService(sourceService)
                .traceId(traceId)
                .build();
    }
}