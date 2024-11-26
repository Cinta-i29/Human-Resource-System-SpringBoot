package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.pojo.entity.Organization;
import com.gdou.service.OrganizationService;
import com.gdou.mapper.OrganizationMapper;
import org.springframework.stereotype.Service;

/**
* @author zzhave
* @description 针对表【organization(机构表)】的数据库操作Service实现
* @createDate 2024-11-25 23:23:48
*/
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization>
    implements OrganizationService{

}




