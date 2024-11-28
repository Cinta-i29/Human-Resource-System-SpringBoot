package com.gdou.pojo.vo.salary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSalaryStandardVo {
    /**
     * 薪酬标准的唯一标识
     */
    @Schema(description = "薪酬标准的唯一标识id")
    private Integer id;

    /**
     * 复核意见
     */
    @Schema(description = "复核意见")
    private String comment;

    /**
     * 薪酬标准项目集合
     */
    @Schema(description = "薪酬标准项目集合")
    private List<RegisterSalaryItemMoneyVo> RegisterSalaryItemMoneyVoList;
}