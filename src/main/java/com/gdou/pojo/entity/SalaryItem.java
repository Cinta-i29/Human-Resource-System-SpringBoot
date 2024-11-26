package com.gdou.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 薪酬项目表
 * @TableName salary_item
 */
@TableName(value ="salary_item")
@Data
public class SalaryItem implements Serializable {
    /**
     * 薪酬项目的唯一标识
     */
    private Integer id;

    /**
     * 薪酬项目名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createdAt;

}