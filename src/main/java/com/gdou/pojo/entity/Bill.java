package com.gdou.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工薪酬账单表
 */
@TableName(value = "bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {
    /**
     * 账单编号
     */
    private Integer id;

    /**
     * 员工档案的编号
     */
    private String employeeId;

    /**
     * 薪酬标准编号
     */
    private Integer salaryStandardId;

    /**
     * 奖金
     */
    private Double awardMoney;

    /**
     * 扣款
     */
    private Double deductionMoney;

    /**
     * 登记人ID
     */
    private Integer registerId;

    /**
     * 登记时间
     */
    private Date registerTime;

    /**
     * 复核人ID
     */
    private Integer reviewId;

    /**
     * 复核时间
     */
    private Date reviewTime;

    /**
     * 状态
     */
    private String status;

}