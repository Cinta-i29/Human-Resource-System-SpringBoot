package com.gdou.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构表
 * @TableName organization
 */
@TableName(value ="organization")
@Data
public class Organization implements Serializable {
    /**
     * 机构编号，主键
     */
    private String id;

    /**
     * 机构层级，用于快速判断机构之间的层级关系
     */
    private Integer level;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 父级机构ID
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

}