package com.gdou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.exception.CustomException;
import com.gdou.mapper.UserMapper;
import com.gdou.pojo.entity.User;
import com.gdou.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzhave
 * @description 针对表【user(用户信息表)】的数据库操作Service实现
 * @createDate 2024-11-25 23:24:05
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    private final UserMapper userMapper;

    public User login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        User tempUser = userMapper.selectOne(wrapper);
        if (tempUser != null && tempUser.getPassword().equals(user.getPassword())) {
            return tempUser;
        }
        throw new CustomException("用户名或密码错误");
    }

    public List<User> getAll() {
        return userMapper.selectList(null);
    }
}




