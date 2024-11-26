package com.gdou.support.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.gdou.mapper.UserMapper;
import com.gdou.pojo.entity.User;
import com.gdou.support.LoginUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoginUserProviderImpl implements LoginUserProvider {
    private final UserMapper userMapper;

    @Override
    public User getLoginUser() {
        String loginId = (String) StpUtil.getTokenInfo().getLoginId();
        return loginId.isBlank() ? null : userMapper.selectById(loginId);
    }
}
