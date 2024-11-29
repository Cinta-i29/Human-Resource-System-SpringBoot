package com.gdou.pojo.vo.bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBillVo {
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
}
