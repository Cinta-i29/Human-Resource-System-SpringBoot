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
public class RegisterSalaryItemMoneyVo {
    /**
     * 薪酬项目的唯一标识
     */
    private Integer id;

    /**
     * 项目金额
     */
    private Double money;
}
