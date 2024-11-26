package com.gdou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.pojo.entity.Organization;
import com.gdou.pojo.vo.org.AddOrgVo;
import com.gdou.pojo.vo.org.Org1Vo;

import java.util.List;

/**
 * @author zzhave
 * @description 针对表【organization(机构表)】的数据库操作Service
 * @createDate 2024-11-26 15:58:47
 */
public interface OrganizationService extends IService<Organization> {
    void addOrganization(AddOrgVo addOrgVo);

    List<Org1Vo> getOrgList();
}
