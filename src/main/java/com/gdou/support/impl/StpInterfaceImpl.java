package com.gdou.support.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.gdou.mapper.UserMapper;
import com.gdou.pojo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 *
 * @author howe
 */
@RequiredArgsConstructor
@Component
public class StpInterfaceImpl implements StpInterface {

    private final UserMapper userMapper;

    /**
     * 返回一个账号所拥有的权限码集合
     * (只做角色鉴权，这里不做权限)
     *
     * @param loginUserIdByString UserType.XXX_STR + id
     * @param loginType           loginType
     * @return 该账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginUserIdByString, String loginType) {
        return new ArrayList<>();
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     *
     * @param loginUserIdByString id
     * @param loginType           loginType
     * @return 该账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginUserIdByString, String loginType) {
        ArrayList<String> list = new ArrayList<>();
        User user = userMapper.selectById(loginUserIdByString.toString());
        list.add(user.getRole());
        return list;
    }
}