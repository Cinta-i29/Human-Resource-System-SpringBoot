package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.pojo.entity.User;
import com.gdou.service.UserService;
import com.gdou.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author zzhave
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2024-11-25 23:24:05
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




