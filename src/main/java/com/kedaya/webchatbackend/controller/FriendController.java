package com.kedaya.webchatbackend.controller;

import com.kedaya.webchatbackend.common.Res;
import com.kedaya.webchatbackend.mapper.UserMapper;
import com.kedaya.webchatbackend.model.vo.FriendApplyRequestVO;
import com.kedaya.webchatbackend.service.FriendService;
import com.kedaya.webchatbackend.utils.JsonUtils;
import com.kedaya.webchatbackend.utils.UserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.controller
 * @Project：webchat-backend
 * @name：FriendController
 * @Date：2025-07-27 22:52
 * @Filename：FriendController
 */
@Slf4j
@RestController
@RequestMapping("friend")
@Tag(name = "好友管理", description = "好友相关接口，包括添加,查询, 删除等功能")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("list")
    @Operation(summary = "通讯录列表", description = "根据用户ID查询好友列表")
    public ResponseEntity list() {
        Long currentUserId = UserUtils.getCurrentUserId();
        return ResponseEntity.ok(Res.success(Collections.emptyList()));
    }

    @PostMapping("/apply")
    @Operation(summary = "好友申请", description = "根据用户ID添加好友请求")
    public ResponseEntity apply(@RequestBody @Validated FriendApplyRequestVO request) {
        log.info("申请加好友: {}", JsonUtils.toJson(request));
        friendService.apply(request.getFriendId(), request.getRemark());
        return ResponseEntity.ok(Res.success("申请成功！"));
    }

    @PostMapping("/agree/{id}")
    @Operation(summary = "同意好友申请", description = "根据申请记录ID同意好友请求")
    public ResponseEntity agree(@PathVariable("id") Long id) {
        log.info("好友申请同意: {}", id);
        return ResponseEntity.ok(Res.success("申请成功！"));
    }
}
