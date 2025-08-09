package com.kedaya.webchatbackend.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.model.vo
 * @Project：webchat-backend
 * @name：FriendApplyRequestVO
 * @Date：2025-07-27 23:12
 * @Filename：FriendApplyRequestVO
 */
@Data
@ToString
public class FriendApplyRequestVO implements Serializable {

    /**
     * 申请加好友的ID
     */
    @NotNull(message = "好友ID不能为空")
    private Long friendId;
}
