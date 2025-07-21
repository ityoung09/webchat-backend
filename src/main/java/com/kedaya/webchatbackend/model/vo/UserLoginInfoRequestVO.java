package com.kedaya.webchatbackend.model.vo;

import com.kedaya.webchatbackend.enums.LoginDeviceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.vo
 * @Project：webchat-backend
 * @name：UserLoginInfoRequestVO
 * @Date：2025-07-16 23:29
 * @Filename：UserLoginInfoRequestVO
 */
@Data
public class UserLoginInfoRequestVO implements Serializable {

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String mobile;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 是否记住我
     */
    private Boolean rememberMe = false;

    @NotNull(message = "登录设备类型不能为空")
    private LoginDeviceType loginDeviceType;
}
