package com.gdou.pojo.vo.bill;

import com.gdou.pojo.vo.salary.SalaryItemMoneyVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author howe
 */
@Data
public class BillListVo {
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
     * 复核意见
     */
    private String reviewOpinion;

    /**
     * 状态
     */
    private String status;

    /**
     * 薪酬标准总额
     */
    private Double salaryStandardTotalMoney;

    /**
     * 薪酬标准名称
     */
    private String salaryStandardName;

    /**
     * 账单总额
     */
    private Double billTotalMoney;

    /**
     * 薪酬项目集合
     */
    private List<SalaryItemMoneyVo> salaryItemMoneyVoList;
}
