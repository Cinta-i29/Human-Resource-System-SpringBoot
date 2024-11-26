package com.gdou.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息表
 *
 * @TableName user
 */
@Data
public class UpdateUser implements Serializable {
    /**
     * 用户ID，主键
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String name;


    /**
     * 用户角色
     */
    private String role;
}