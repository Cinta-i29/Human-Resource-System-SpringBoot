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
public class ReviewBillVo {
    /**
     * 账单编号
     */
    private Integer id;

    /**
     * 奖金
     */
    private Double awardMoney;

    /**
     * 扣款
     */
    private Double deductionMoney;

    /**
     * 复核意见
     */
    private String reviewOpinion;
}
