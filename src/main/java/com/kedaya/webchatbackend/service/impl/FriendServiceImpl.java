package com.kedaya.webchatbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kedaya.webchatbackend.enums.FriendStatusEnum;
import com.kedaya.webchatbackend.exception.BusinessException;
import com.kedaya.webchatbackend.model.entity.FriendInfo;
import com.kedaya.webchatbackend.model.entity.UserInfo;
import com.kedaya.webchatbackend.repository.FriendRepository;
import com.kedaya.webchatbackend.service.FriendService;
import com.kedaya.webchatbackend.service.UserService;
import com.kedaya.webchatbackend.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.service.impl
 * @Project：webchat-backend
 * @name：FriendServiceImpl
 * @Date：2025-07-28 20:30
 * @Filename：FriendServiceImpl
 */
@Slf4j
@Service
public class FriendServiceImpl extends ServiceImpl<FriendRepository, FriendInfo> implements FriendService {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public void apply(Long friendId, String remark) {
        Long currentUserId = UserUtils.getCurrentUserId();
        Assert.isTrue(!currentUserId.equals(friendId), "申请失败：不能添加自己好友呃～");
        UserInfo friendUserInfo = userService.getById(friendId);
        Assert.isTrue(friendUserInfo != null, "申请失败：好友不存在！");
        FriendInfo friendInfo = findInfoByUserIdAndFriendId(currentUserId, friendId);
        if (friendInfo == null) {
            friendInfo = new FriendInfo();
            friendInfo.setUserId(currentUserId);
            friendInfo.setFriendId(friendId);
            friendInfo.setRemark(remark);
            friendInfo.setApplyDate(LocalDateTime.now());
            friendInfo.setStatus(FriendStatusEnum.APPLY.getStatus());
        } else {
            if (FriendStatusEnum.PASS.getStatus().equals(friendInfo.getStatus())) {
                throw new BusinessException("已经是好友了,不需要再次申请！");
            } else  {
                // 再次申请，修改申请时间，但保持一条记录
                friendInfo.setRemark(remark);
                friendInfo.setApplyDate(LocalDateTime.now());
                friendInfo.setHandleDate(null);
            }
        }
        saveOrUpdate(friendInfo);
    }



    public FriendInfo findInfoByUserIdAndFriendId(Long userId, Long friendId) {
        QueryWrapper query = new QueryWrapper();
        query.eq("user_id", userId);
        query.eq("friend_id", friendId);
        return getOne(query);
    }
}
