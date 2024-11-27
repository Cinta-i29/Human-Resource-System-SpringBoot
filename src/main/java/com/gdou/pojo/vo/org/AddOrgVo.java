package com.gdou.pojo.vo.org;

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
public class AddOrgVo {
    /**
     * 机构名称
     */
    @Schema(description = "机构名称")
    private String name;

    /**
     * 机构级别
     */
    @Schema(description = "机构等级(1,2,3)")
    private Integer level;

    /**
     * 一级机构code
     */
    @Schema(description = "一级机构code，当机构登记为2,3时需要填")
    private Integer firstCode;

    /**
     * 二级机构code
     */
    @Schema(description = "二级机构code，当机构登记为3时需要填")
    private Integer secondCode;
}
