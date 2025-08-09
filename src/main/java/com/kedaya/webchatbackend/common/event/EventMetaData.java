package com.kedaya.webchatbackend.common.event;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.common.event
 * @Project：webchat-backend
 * @name：EventMetadata
 * @Date：2025-07-28 00:30
 * @Filename：EventMetadata
 */
@Data
@Builder
public class EventMetaData {

    // 事件唯一标识
    private String eventId;

    // 事件发生时间
    private LocalDateTime timestamp;

    // 事件类型（用于分类和路由）
    private String eventType;

    // 事件版本（用于事件演进和兼容性）
    private String version;

    // 事件来源服务（微服务环境下很有用）
    private String sourceService;

    // 追踪ID（用于分布式链路追踪）
    private String traceId;
}
