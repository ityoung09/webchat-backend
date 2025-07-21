package com.kedaya.webchatbackend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.common
 * @Project：webchat-backend
 * @name：Res
 * @Date：2025-07-17 00:02
 * @Filename：Res
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Res<T> implements Serializable {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 错误详情
     */
    private String errors;

    /**
     * 数据
     */
    private T data;

    public static <T> Res<T> success(T data) {
        return new Res<>("200", "请求成功", null, data);
    }

    public static <T> Res<T> success(String message) {
        return new Res<>("200", message, null, null);
    }

    public static <T> Res<T> fail(String code, String message, String errors) {
        return new Res<>(code, message, errors, null);
    }

    public static <T> Res<T> fail(String code, String errors) {
        return new Res<>(code, null, errors, null);
    }
}
