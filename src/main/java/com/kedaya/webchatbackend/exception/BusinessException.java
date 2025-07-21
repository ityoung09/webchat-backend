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
     * 构造函数
     *
     * @param errorCode 错误码枚举
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 构造函数
     *
     * @param errorCode     错误码枚举
     * @param detailMessage 详细错误信息
     */
    public BusinessException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     * @param cause     原始异常
     */
    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * 构造函数
     *
     * @param errorCode     错误码枚举
     * @param detailMessage 详细错误信息
     * @param cause         原始异常
     */
    public BusinessException(ErrorCode errorCode, String detailMessage, Throwable cause) {
        super(errorCode, detailMessage, cause);
    }

    /**
     * 构造函数（自定义错误码和错误信息）
     *
     * @param message 错误信息
     */
    public BusinessException(String message) {
        super(CODE, message);
    }

    /**
     * 构造函数（自定义错误码和错误信息）
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public BusinessException(String code, String message) {
        super(code, message);
    }

    /**
     * 构造函数（自定义错误码和错误信息）
     *
     * @param code          错误码
     * @param message       错误信息
     * @param detailMessage 详细错误信息
     */
    public BusinessException(String code, String message, String detailMessage) {
        super(code, message, detailMessage);
    }
}
