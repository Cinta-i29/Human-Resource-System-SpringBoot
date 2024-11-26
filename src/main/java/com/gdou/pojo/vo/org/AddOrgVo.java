package com.gdou.pojo.vo.org;

import io.swagger.v3.oas.annotations.Parameter;
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
    @Parameter(description = "机构名称")
    private String name;

    /**
     * 机构级别
     */
    @Parameter(description = "机构等级(1,2,3)")
    private Integer level;

    /**
     * 一级机构code
     */
    @Parameter(description = "一级机构code，当机构登记为2,3时需要填")
    private Integer firstCode;

    /**
     * 二级机构code
     */
    @Parameter(description = "二级机构code，当机构登记为3时需要填")
    private Integer secondCode;
}
