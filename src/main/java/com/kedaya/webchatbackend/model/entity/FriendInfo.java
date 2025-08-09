package com.kedaya.webchatbackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "web_chat_friend")
public class FriendInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 好友ID
     */
    private Long friendId;

    /**
     * 好友状态
     */
    private Integer status;

    /**
     * 申请时间
     */
    private LocalDateTime applyDate;

    /**
     * 处理时间
     */
    private LocalDateTime handleDate;
}