package com.gdou.mapping;

import com.gdou.pojo.entity.EmployeeRecord;
import com.gdou.pojo.vo.employee.AddEmployeeRecordVo;
import org.mapstruct.Mapper;

/**
 * @author howe
 */
@Mapper(componentModel = "spring")
public interface EmployeeRecordMapping {
    EmployeeRecord addEmployeeRecordVoToEmployeeRecord(AddEmployeeRecordVo addEmployeeRecordVo);
}
