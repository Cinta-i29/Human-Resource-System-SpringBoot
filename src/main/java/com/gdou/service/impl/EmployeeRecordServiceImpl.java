package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.pojo.entity.EmployeeRecord;
import com.gdou.service.EmployeeRecordService;
import com.gdou.mapper.EmployeeRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author zzhave
* @description 针对表【employee_record(员工档案表)】的数据库操作Service实现
* @createDate 2024-11-25 23:23:28
*/
@Service
public class EmployeeRecordServiceImpl extends ServiceImpl<EmployeeRecordMapper, EmployeeRecord>
    implements EmployeeRecordService{

}




