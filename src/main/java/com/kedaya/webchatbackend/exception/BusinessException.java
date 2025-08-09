package com.kedaya.webchatbackend.exception;

/**
 * 业务异常类
 * 用于处理业务逻辑相关的异常
 *
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.exception
 * @Project：webchat-backend
 * @name：BusinessException
 * @Date：2025-07-17
 * @Filename：BusinessException
 */
public class BusinessException extends BaseException {

    private final static String CODE = "500";

    /**
     * 构造函数（自定义错误码和错误信息）
     *
     * @param message 错误信息
     */
    public BusinessException(String message) {
        super(CODE, message);
    }

    public BusinessException(String message, Throwable cause) {
        super(CODE, message, cause);
    }

}
