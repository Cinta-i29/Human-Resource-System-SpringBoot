package com.gdou.pojo.vo.bill;

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
public class ConditionalSearchBillVo {
    @Schema(description = "账单编号")
    private Integer id;

    @Schema(description = "员工档案编号")
    private String employeeId;

    @Schema(description = "薪酬标准编号")
    private Integer salaryStandardId;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "登记开始时间")
    private String registerStartTime;

    @Schema(description = "登记结束时间")
    private String registerEndTime;
}
