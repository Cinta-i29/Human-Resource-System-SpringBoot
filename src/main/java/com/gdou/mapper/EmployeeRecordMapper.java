package com.gdou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.pojo.entity.EmployeeRecord;
import com.gdou.pojo.vo.employee.ConditionalQueriesEmployeeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author zzhave
* @description 针对表【employee_record(员工档案表)】的数据库操作Mapper
* @createDate 2024-11-25 23:23:28
* @Entity com.gdou.pojo.entity.EmployeeRecord
*/
@Mapper
public interface EmployeeRecordMapper extends BaseMapper<EmployeeRecord> {

    List<String> getMaxRecordNum(String formattedCode);

    EmployeeRecord selectByRecordNumber(String recordNumber);

}




