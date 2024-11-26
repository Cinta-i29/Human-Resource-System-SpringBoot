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
public class Org1Vo {
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
     * 二级机构code
     */
    private List<Org2Vo> childrenOrg2;
}
