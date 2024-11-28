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
public class ConditionalSearchBillEmpVo {
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
}
