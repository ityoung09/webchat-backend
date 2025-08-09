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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制器
 * 提供用户注册、登录、登出、信息查询等功能
 *
 * @Author：CHENWEI
 * @Date：2025-07-16 23:22
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理", description = "用户相关接口，包括注册、登录、登出、信息查询等功能")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param request 登录请求信息
     * @return 登录成功返回Token信息
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated UserLoginInfoRequestVO request) {
        UserLoginInfoRequestDTO loginDto = userMapper.loginVoToDto(request);
        SaTokenInfo tokenInfo = userService.login(loginDto);
        return ResponseEntity.ok(Res.success(tokenInfo));
    }

    /**
     * 用户登出
     *
     * @return 登出结果
     */
    @PostMapping("/logout")
    public ResponseEntity logout() {
        StpUtil.logout();
        return ResponseEntity.ok(Res.success("退出成功！"));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated UserRegisterInfoRequestVO request) {
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
}
