package com.gdou.mapping;

import com.gdou.pojo.entity.EmployeeRecord;
import com.gdou.pojo.vo.employee.AddEmployeeRecordVo;
import com.gdou.pojo.vo.employee.UpdateEmployeeRecordVo;
import com.gdou.pojo.vo.employee.UpdateEmployeeRecordVoFromHrmVo;
import org.mapstruct.Mapper;

/**
 * @author howe
 */
@Mapper(componentModel = "spring")
public interface EmployeeRecordMapping {
    EmployeeRecord addEmployeeRecordVoToEmployeeRecord(AddEmployeeRecordVo addEmployeeRecordVo);

    EmployeeRecord updateEmployeeRecordVoToEmployeeRecord(UpdateEmployeeRecordVo updateEmployeeRecordVo);

    EmployeeRecord updateEmployeeRecordVoFromHrmVoToEmployeeRecord(UpdateEmployeeRecordVoFromHrmVo updateEmployeeRecordVo);
}
