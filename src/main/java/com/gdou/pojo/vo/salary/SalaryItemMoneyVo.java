package com.gdou.pojo.vo.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryItemMoneyVo {
    /**
     * 薪酬项目的唯一标识
     */
    private Integer id;

    /**
     * 薪酬项目名称
     */
    private String name;

    /**
     * 是否为扣款项
     */
    private Boolean isDeduction;

    /**
     * 项目金额
     */
    private Double money;
}
