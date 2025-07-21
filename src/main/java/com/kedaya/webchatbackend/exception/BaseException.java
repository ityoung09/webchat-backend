package com.kedaya.webchatbackend.exception;

import lombok.Getter;

/**
 * 基础异常类
 * 所有自定义异常的父类
 * 
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.exception
 * @Project：webchat-backend
 * @name：BaseException
 * @Date：2025-07-17
 * @Filename：BaseException
 */
@Getter
public class BaseException extends RuntimeException {

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误信息
     */
    private final String message;

    /**
     * 详细错误信息（用于日志记录，不返回给前端）
     */
    private final String detailMessage;

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     */
    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.detailMessage = null;
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     * @param detailMessage 详细错误信息
     */
    public BaseException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.detailMessage = detailMessage;
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     * @param cause 原始异常
     */
    public BaseException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.detailMessage = cause != null ? cause.getMessage() : null;
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     * @param detailMessage 详细错误信息
     * @param cause 原始异常
     */
    public BaseException(ErrorCode errorCode, String detailMessage, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.detailMessage = detailMessage;
    }

    /**
     * 构造函数（自定义错误码和错误信息）
     *
     * @param code 错误码
     * @param message 错误信息
     */
    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
        this.detailMessage = null;
    }

    /**
     * 构造函数（自定义错误码和错误信息）
     *
     * @param code 错误码
     * @param message 错误信息
     * @param detailMessage 详细错误信息
     */
    public BaseException(String code, String message, String detailMessage) {
        super(message);
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    /**
     * 构造函数（自定义错误码和错误信息）
     *
     * @param code 错误码
     * @param message 错误信息
     * @param cause 原始异常
     */
    public BaseException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.detailMessage = cause != null ? cause.getMessage() : null;
    }

    /**
     * 获取完整的错误信息（包含详细信息）
     *
     * @return 完整错误信息
     */
    public String getFullMessage() {
        if (detailMessage != null && !detailMessage.isEmpty()) {
            return message + " - " + detailMessage;
        }
        return message;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", detailMessage='" + detailMessage + '\'' +
                '}';
    }
}
