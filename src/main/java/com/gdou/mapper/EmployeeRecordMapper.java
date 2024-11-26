package com.gdou.mapper;

import com.gdou.pojo.entity.EmployeeRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zzhave
* @description 针对表【employee_record(员工档案表)】的数据库操作Mapper
* @createDate 2024-11-25 23:23:28
* @Entity com.gdou.pojo.entity.EmployeeRecord
*/
@Mapper
public interface EmployeeRecordMapper extends BaseMapper<EmployeeRecord> {

}




