package com.kedaya.webchatbackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.entity
 * @Project：webchat-backend
 * @name：UserInfo
 * @Date：2025-07-16 22:48
 * @Filename：UserInfo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "web_chat_user")
public class UserInfo extends BaseEntity implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户头像
     */
    private String photo;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户状态状态
     */
    private Integer status;

    /**
     * 角色
     */
    private Integer roleCode;
}
