package com.kedaya.webchatbackend.controller;

import com.kedaya.webchatbackend.common.Res;
import com.kedaya.webchatbackend.model.vo.FriendApplyRequestVO;
import com.kedaya.webchatbackend.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class FriendController {

    @PostMapping("/apply")
    public ResponseEntity apply(@RequestBody @Validated FriendApplyRequestVO request) {
        log.info("申请加好友: {}", JsonUtils.toJson(request));
        return ResponseEntity.ok(Res.success("申请成功！"));
    }
}
