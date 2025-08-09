package com.kedaya.webchatbackend.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户基本信息视图对象
 *
 * @Author：CHENWEI
 * @Date：2025-07-21 23:23
 */
@Data
public class UserBaseInfoVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 头像
     */
    private String photo;

    /***
     * 性别
     */
    private String sex;

    /**
     * 签名
     */
    private String signature;

    /**
     * 用户名
     */
    private String userName;

    /***
     * 手机号
     */
    private String mobile;

    private Integer roleCode;

    /***
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registryTime;

}
