package com.kedaya.webchatbackend.exception;

/**
 * 业务异常类
 * 用于处理业务逻辑相关的异常
 *
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.exception
 * @Project：webchat-backend
 * @name：EventPublishException
 * @Date：2025-07-28
 * @Filename：EventPublishException
 */
public class EventPublishException extends BaseException {

    private final static String CODE = "501";

    public EventPublishException(String message) {
        super(CODE, message);
    }

    public EventPublishException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}
