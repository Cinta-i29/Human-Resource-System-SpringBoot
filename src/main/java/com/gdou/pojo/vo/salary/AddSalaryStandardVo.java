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
public class AddSalaryStandardVo {
    /**
     * 薪酬标准名称
     */
    @Schema(description = "薪酬标准名称")
    private String name;

    /**
     * 薪酬的项目ID
     */
    @Schema(description = "薪酬的项目ID集合")
    private List<Integer> salaryItemIdList;

}
