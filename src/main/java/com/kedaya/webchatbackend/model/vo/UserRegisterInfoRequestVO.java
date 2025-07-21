package com.kedaya.webchatbackend.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.vo
 * @Project：webchat-backend
 * @name：UserRegisterInfoRequestVO
 * @Date：2025-07-17 23:29
 * @Filename：UserRegisterInfoRequestVO
 */
@Data
public class UserRegisterInfoRequestVO implements Serializable {

    /**
     * 用户名
     */
    @NotBlank(message = "手机号不能为空")
    @Length(min = 2, max = 20, message = "用户名长度必须在2-20之间")
    private String userName;

    private String email;

    /**
     * 手机号码
     * 唯一用于登陆
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String mobile;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$", message = "密码必须包含字母和数字，长度为6-20位")
    private String password;
}
