package com.kedaya.webchatbackend.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kedaya.webchatbackend.enums.LoginDeviceType;
import com.kedaya.webchatbackend.exception.BusinessException;
import com.kedaya.webchatbackend.mapper.UserMapper;
import com.kedaya.webchatbackend.model.dto.UserBaseInfoDTO;
import com.kedaya.webchatbackend.model.dto.UserLoginInfoRequestDTO;
import com.kedaya.webchatbackend.model.dto.UserRegisterInfoRequestDTO;
import com.kedaya.webchatbackend.model.entity.UserInfo;
import com.kedaya.webchatbackend.repository.UserRepository;
import com.kedaya.webchatbackend.service.UserService;
import com.kedaya.webchatbackend.utils.AvatarUtils;
import com.kedaya.webchatbackend.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.service.impl
 * @Project：webchat-backend
 * @name：UserServiceImpl
 * @Date：2025-07-16 23:18
 * @Filename：UserServiceImpl
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, UserInfo> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public SaTokenInfo login(UserLoginInfoRequestDTO loginDto) {
        String mobile = loginDto.getMobile();
        String password = loginDto.getPassword();
        LoginDeviceType loginDeviceType = loginDto.getLoginDeviceType();
        // 校验账号密码是否正确
        UserInfo userInfo = selectInfoByMobile(mobile);
        if (ObjectUtils.isEmpty(userInfo)) {
            throw new BusinessException("当前账号不存在！");
        }
        if (!PasswordUtils.verifyPassword(password, userInfo.getPassword())) {
            throw new BusinessException("请检查输入的密码是否错误！");
        }
        StpUtil.login(userInfo.getId(), loginDeviceType.getCode());
        return StpUtil.getTokenInfo();
    }

    @Override
    public void register(UserRegisterInfoRequestDTO request) {
        String mobile = request.getMobile();
        String password = request.getPassword();
        String userName = request.getUserName();
        UserInfo userInfo = selectInfoByMobile(mobile);
        if (userInfo != null) {
            throw new BusinessException("当前手机号码已注册！");
        }
        String hashPassword = PasswordUtils.hashPassword(password);
        UserInfo saveInfo = UserInfo.builder().mobile(mobile).password(hashPassword).userName(userName).photo(AvatarUtils.getRandomAvatar()).build();
        save(saveInfo);
    }

    @Override
    public UserBaseInfoDTO findBaseInfoById(Long id) {
        UserInfo userInfo = getById(id);
        UserBaseInfoDTO userBaseInfoDTO = userMapper.entityToBaseDto(userInfo);
        return userBaseInfoDTO;
    }

    private UserInfo selectInfoByMobile(String mobile) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper();
        wrapper.eq("mobile", mobile);
        return getOne(wrapper);
    }
}
