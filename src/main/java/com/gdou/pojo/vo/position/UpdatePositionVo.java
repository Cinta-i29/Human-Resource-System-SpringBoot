package com.gdou.pojo.vo.position;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 职位表
 * @TableName position
 */
@TableName(value ="position")
@Data
public class UpdatePositionVo implements Serializable {
    /**
     * 职位的唯一标识
     */
    private Integer id;

    /**
     * 职位名称
     */
    private String name;
}