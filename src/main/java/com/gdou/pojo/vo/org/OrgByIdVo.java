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
public class OrgByIdVo {
    private Integer orgId;
    private String firstOrgCode;
    private String firstOrgName;
    private String secondOrgCode;
    private String secondOrgName;
    private String thirdOrgCode;
    private String thirdOrgName;
}
