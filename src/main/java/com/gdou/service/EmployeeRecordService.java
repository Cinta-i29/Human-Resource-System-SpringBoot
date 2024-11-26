package com.gdou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.pojo.entity.EmployeeRecord;
import com.gdou.pojo.vo.employee.AddEmployeeRecordVo;

/**
 * @author zzhave
 * @description 针对表【employee_record(员工档案表)】的数据库操作Service
 * @createDate 2024-11-25 23:23:28
 */
public interface EmployeeRecordService extends IService<EmployeeRecord> {

    EmployeeRecord addEmployeeRecord(AddEmployeeRecordVo addEmployeeRecordVo);
}
