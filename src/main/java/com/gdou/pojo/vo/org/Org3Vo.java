package com.gdou.pojo.vo.org;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Org3Vo {
    /**
     * 机构id
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
     * 机构code
     */
    private Integer code;

    /**
     * 父级机构code
     */
    private Integer parentCode;
}
