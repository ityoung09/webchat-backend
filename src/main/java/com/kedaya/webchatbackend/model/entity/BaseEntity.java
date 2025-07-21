package com.kedaya.webchatbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.entity
 * @Project：webchat-backend
 * @name：BaseEntity
 * @Date：2025-07-16 21:24
 * @Filename：BaseEntity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {

    @TableId
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private Long createdBy;

    @TableField(fill = FieldFill.UPDATE)
    private Long updatedBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdDate;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updatedDate;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;

    @Version
    private Integer version;

}
