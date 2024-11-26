package com.gdou.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 薪酬标准表
 * @TableName salary_standard
 */
@TableName(value ="salary_standard")
@Data
public class SalaryStandard implements Serializable {
    /**
     * 薪酬标准的唯一标识
     */
    private Integer id;

    /**
     * 薪酬标准名称
     */
    private String name;

    /**
     * 制定人id
     */
    private Integer creatorId;

    /**
     * 登记人id
     */
    private Integer registrarId;

    /**
     * 状态
     */
    private String status;

    /**
     * 登记时间
     */
    private Date createdAt;

    /**
     * 复核意见
     */
    private String comment;

    /**
     * 更新时间
     */
    private Date updatedAt;

}