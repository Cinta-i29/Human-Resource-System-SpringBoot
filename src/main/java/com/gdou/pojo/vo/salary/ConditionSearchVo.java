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
public class ConditionSearchVo {
    /**
     * 薪酬标准的唯一标识
     */
    private Integer id;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 登记开始时间
     */
    private String startTime;

    /**
     * 登记结束时间
     */
    private String endTime;
}
