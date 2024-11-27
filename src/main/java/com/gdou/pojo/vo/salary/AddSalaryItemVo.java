package com.gdou.pojo.vo.salary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 薪酬项目表
 */
@Data
public class AddSalaryItemVo implements Serializable {
    /**
     * 薪酬项目名称
     */
    @Schema(description = "薪酬项目名称")
    private String name;

    /**
     * 是否为扣款项
     */
    @Schema(description = "是否为扣款项,true为是扣款项,false为否")
    private Boolean isDeduction;

}