package com.gdou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.pojo.entity.Organization;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author howe
 */
public interface OrganizationMapper extends BaseMapper<Organization> {
    @Select("SELECT * FROM organization ORDER BY level, code")
    List<Organization> selectAllOrdered();

    Integer getOrgByCode(Integer code1, Integer code2, Integer code3);
}




