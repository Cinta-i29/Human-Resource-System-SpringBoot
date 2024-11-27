package com.gdou.pojo.vo.employee;

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
public class ConditionalQueriesEmployeeVo {
    /**
     * 一级机构code
     */
    @Schema(description = "一级机构code")
    private String firstCode;

    /**
     * 二级机构code
     */
    @Schema(description = "二级机构code")
    private String secondCode;

    /**
     * 三级机构code
     */
    @Schema(description = "三级机构code")
    private String thirdCode;

    /**
     * 职位id
     */
    @Schema(description = "职位id")
    private String positionId;

    /**
     * 建档开始时间
     */
    @Schema(description = "建档开始时间")
    private String startTime;

    /**
     * 建档结束时间
     */
    @Schema(description = "建档结束时间")
    private String endTime;
}
