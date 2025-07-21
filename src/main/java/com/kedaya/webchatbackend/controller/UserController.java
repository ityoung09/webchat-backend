package com.kedaya.webchatbackend.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.kedaya.webchatbackend.common.Res;
import com.kedaya.webchatbackend.mapper.UserMapper;
import com.kedaya.webchatbackend.model.dto.UserBaseInfoDTO;
import com.kedaya.webchatbackend.model.dto.UserLoginInfoRequestDTO;
import com.kedaya.webchatbackend.model.dto.UserRegisterInfoRequestDTO;
import com.kedaya.webchatbackend.model.vo.UserBaseInfoVO;
import com.kedaya.webchatbackend.model.vo.UserLoginInfoRequestVO;
import com.kedaya.webchatbackend.model.vo.UserRegisterInfoRequestVO;
import com.kedaya.webchatbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.controller
 * @Project：webchat-backend
 * @name：UserController
 * @Date：2025-07-16 23:22
 * @Filename：UserController
 */
@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserLoginInfoRequestVO request) {
        UserLoginInfoRequestDTO loginDto = userMapper.loginVoToDto(request);
        SaTokenInfo tokenInfo = userService.login(loginDto);
        return ResponseEntity.ok(Res.success(tokenInfo));
    }

    /**
     * 用户登出
     *
     * @param
     * @return
     */
    @PostMapping("/logout")
    public ResponseEntity logout() {
        StpUtil.logout();
        return ResponseEntity.ok(Res.success("退出成功！"));
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserRegisterInfoRequestVO request) {
        UserRegisterInfoRequestDTO registerDto = userMapper.registerVoToDto(request);
        userService.register(registerDto);
        return ResponseEntity.ok(Res.success("注册成功，请登录！"));
    }

    @GetMapping("/info/{id}")
    public ResponseEntity getUserInfo(@PathVariable("id") Long id) {
        UserBaseInfoDTO userBaseInfoDto = userService.findBaseInfoById(id);
        UserBaseInfoVO userBaseInfoVo = userMapper.baseDtoToVo(userBaseInfoDto);
        return ResponseEntity.ok(Res.success(userBaseInfoVo));
    }

    @GetMapping("/hi")
    public ResponseEntity hi() {
        return ResponseEntity.ok(Res.success("hi"));
    }
}
