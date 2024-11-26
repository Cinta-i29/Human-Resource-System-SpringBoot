package com.gdou.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构关联表
 * @TableName organization_relation
 */
@TableName(value ="organization_relation")
@Data
public class OrganizationRelation implements Serializable {
    /**
     * 一级机构id
     */
    private String firstLevelId;

    /**
     * 二级机构id
     */
    private String secondLevelId;

    /**
     * 三级机构id
     */
    private String thirdLevelId;

    /**
     * 薪酬月份
     */
    private String month;

    /**
     * 薪酬编号
     */
    private Integer salaryNumber;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

}