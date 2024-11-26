package com.gdou.support;

import com.gdou.pojo.entity.User;

/**
 * @author Raqtpie
 */
public interface LoginUserProvider {
    /**
     * 获取学生登录用户
     * @return 学生登录用户
     */
    User getLoginUser();
}
