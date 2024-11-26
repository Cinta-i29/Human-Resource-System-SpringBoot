package com.gdou.pojo.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginSuccessVo {

    /**
     * 用户ID，主键
     */
    private Integer userId;

    /**
     * 用户登录名
     */
    private String name;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户角色
     */
    private String role;

    /**
     * 用户token
     */
    private String token;
}
