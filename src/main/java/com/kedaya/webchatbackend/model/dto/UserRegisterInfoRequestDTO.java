package com.kedaya.webchatbackend.model.dto;

import lombok.Data;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.model.dto
 * @Project：webchat-backend
 * @name：UserRegisterInfoRequestDTO
 * @Date：2025-07-21 23:39
 * @Filename：UserRegisterInfoRequestDTO
 */
@Data
public class UserRegisterInfoRequestDTO {

    /**
     * 用户名
     */
    private String userName;

    private String email;

    /**
     * 手机号码
     * 唯一用于登陆
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;
}
