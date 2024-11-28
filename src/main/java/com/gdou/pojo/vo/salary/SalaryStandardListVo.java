package com.gdou.pojo.vo.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryStandardListVo {
    /**
     * 薪酬标准的唯一标识
     */
    private Integer id;

    /**
     * 薪酬标准名称
     */
    private String name;

    /**
     * 制定人id
     */
    private Integer creatorId;

    /**
     * 制定时间
     */
    private Date creatorAt;

    /**
     * 登记人id
     */
    private Integer registrarId;

    /**
     * 登记时间
     */
    private Date registrarAt;

    /**
     * 复核人id
     */
    private Integer reviewId;

    /**
     * 复核时间
     */
    private Date reviewAt;

    /**
     * 状态
     */
    private String status;

    /**
     * 复核意见
     */
    private String reviewComment;

    /**
     * 薪酬项目集合
     */
    private List<SalaryItemMoneyVo> SalaryItemMoneyVoList;
}
