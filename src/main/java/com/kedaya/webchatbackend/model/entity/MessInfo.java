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
@TableName(value = "web_chat_mess")
public class MessInfo extends BaseEntity implements Serializable {

    /**
     * 发送人
     */
    private Long senderId;

    /**
     * 接收人
     */
    private Long receiverId;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 图片
     */
    private String image;

    /**
     * 是否已读
     */
    private Boolean isRead;

    /**
     * 消息时间
     */
    private LocalDateTime sendDate;
}