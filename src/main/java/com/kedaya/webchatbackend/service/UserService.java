package com.kedaya.webchatbackend.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kedaya.webchatbackend.model.dto.UserBaseInfoDTO;
import com.kedaya.webchatbackend.model.dto.UserLoginInfoRequestDTO;
import com.kedaya.webchatbackend.model.dto.UserRegisterInfoRequestDTO;
import com.kedaya.webchatbackend.model.entity.UserInfo;

public interface UserService extends IService<UserInfo> {

    SaTokenInfo login(UserLoginInfoRequestDTO loginDto);

    void register(UserRegisterInfoRequestDTO registerDto);

    UserBaseInfoDTO findBaseInfoById(Long id);

    UserBaseInfoDTO findBaseInfoByMobile(String mobile);
}
