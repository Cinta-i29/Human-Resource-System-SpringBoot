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
     * 主键id
     */
    private Integer id;

    /**
     * 机构级别
     */
    private Integer level;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 父级机构Id
     */
    private Integer parentId;

    /**
     * 机构code
     */
    private Integer code;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

}