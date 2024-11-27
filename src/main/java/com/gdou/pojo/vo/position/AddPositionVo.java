package com.gdou.pojo.vo.position;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 职位表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPositionVo implements Serializable {
    /**
     * 职位名称
     */
    @Parameter(description = "职位名称")
    private String name;
}