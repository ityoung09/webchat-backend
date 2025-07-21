package com.kedaya.webchatbackend.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.model.dto
 * @Project：webchat-backend
 * @name：UserBaseInfoDTO
 * @Date：2025-07-21 23:50
 * @Filename：UserBaseInfoDTO
 */
@Data
public class UserBaseInfoDTO {

    /**
     * 用户ID
     */
    private Long userId;

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
     * 用户状态状态
     */
    private Integer status;

    /**
     * 角色
     */
    private Integer roleCode;

    /**
     * 注册时间
     */
    private LocalDateTime registryTime;
}
