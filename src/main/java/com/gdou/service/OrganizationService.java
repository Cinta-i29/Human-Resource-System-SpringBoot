package com.gdou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.pojo.entity.Organization;
import com.gdou.pojo.vo.org.AddOrgVo;
import com.gdou.pojo.vo.org.Org1Vo;
import com.gdou.pojo.vo.org.OrgByIdVo;

import java.util.List;

/**
 * @author zzhave
 */
public interface OrganizationService extends IService<Organization> {
    void addOrganization(AddOrgVo addOrgVo);

    List<Org1Vo> getOrgList();

    Integer getOrgByCode(Integer code1, Integer code2, Integer code3);

    /**
     * 根据机构id查询机构信息
     */
    OrgByIdVo getOrgById(Integer orgId);
}
