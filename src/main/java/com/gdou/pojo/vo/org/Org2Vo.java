package com.gdou.pojo.vo.org;

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
public class Org2Vo {
    /**
     * 机构级别
     */
    private Integer level;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 机构code
     */
    private Integer code;

    /**
     * 父级机构code
     */
    private Integer parentCode;

    /**
     * 三级机构列表
     */
    private List<Org3Vo> childrenOrg3;
}
