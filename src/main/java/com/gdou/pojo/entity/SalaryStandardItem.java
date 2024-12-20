package com.gdou.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 薪酬标准与薪酬项目关系表
 *
 * @TableName salary_standard_item
 */
@TableName(value = "salary_standard_item")
@Data
public class SalaryStandardItem implements Serializable {
    /**
     * 所属薪酬标准
     */
    private Integer salaryStandardId;

    /**
     * 对应的薪酬项目
     */
    private Integer salaryItemId;

    /**
     * 该项目对应的金额
     */
    private Double money;
}