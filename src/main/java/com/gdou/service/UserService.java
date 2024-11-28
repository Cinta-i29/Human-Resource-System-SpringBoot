package com.gdou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.pojo.entity.User;

import java.util.List;

/**
 * @author zzhave
 * @description 针对表【user(用户信息表)】的数据库操作Service
 * @createDate 2024-11-25 23:24:05
 */
public interface UserService extends IService<User> {

    User login(User user);

    List<User> getAll();

    void saveUser(User user);
}
