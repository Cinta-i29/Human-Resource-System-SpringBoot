package com.gdou.mapper;

import com.gdou.pojo.entity.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zzhave
* @description 针对表【organization(机构表)】的数据库操作Mapper
* @createDate 2024-11-25 23:23:48
* @Entity com.gdou.pojo.entity.Organization
*/
@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {

}




