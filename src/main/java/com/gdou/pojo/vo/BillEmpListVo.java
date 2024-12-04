package com.gdou.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillEmpListVo {
    @Schema(description = "档案编号")
    private String recordNumber;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "职位名称")
    private String position_name;
    @Schema(description = "职称")
    private String title;
    @Schema(description = "薪酬标准id")
    private Integer salary_standard_id;
    @Schema(description = "薪酬标准名称")
    private String salary_standard_name;
    @Schema(description = "薪酬标准总额")
    private String salary_standard_amount;
}