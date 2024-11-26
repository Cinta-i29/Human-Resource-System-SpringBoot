package com.gdou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.pojo.entity.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author zzhave
* @description 针对表【organization(机构表)】的数据库操作Mapper
* @createDate 2024-11-26 15:58:47
* @Entity com.gdou.domain.Organization
*/
@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {
    @Select("SELECT * FROM organization ORDER BY level, code")
    List<Organization> selectAllOrdered();
}




