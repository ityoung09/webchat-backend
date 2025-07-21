package com.kedaya.webchatbackend.model.dto;

import com.kedaya.webchatbackend.enums.LoginDeviceType;
import lombok.Data;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.model.dto
 * @Project：webchat-backend
 * @name：UserLoginInfoRequestDTO
 * @Date：2025-07-21 23:44
 * @Filename：UserLoginInfoRequestDTO
 */
@Data
public class UserLoginInfoRequestDTO {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否记住我
     */
    private Boolean rememberMe = false;

    /**
     * 登录设备类型
     */
    private LoginDeviceType loginDeviceType;
}
