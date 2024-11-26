package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.exception.CustomException;
import com.gdou.mapper.EmployeeRecordMapper;
import com.gdou.mapping.EmployeeRecordMapping;
import com.gdou.pojo.entity.EmployeeRecord;
import com.gdou.pojo.vo.employee.AddEmployeeRecordVo;
import com.gdou.service.EmployeeRecordService;
import com.gdou.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author zzhave
 * @description 针对表【employee_record(员工档案表)】的数据库操作Service实现
 * @createDate 2024-11-25 23:23:28
 */
@Service
@RequiredArgsConstructor
public class EmployeeRecordServiceImpl extends ServiceImpl<EmployeeRecordMapper, EmployeeRecord> implements EmployeeRecordService {
    private final EmployeeRecordMapping employeeRecordMapping;
    private final OrganizationService organizationService;

    @Override
    public EmployeeRecord addEmployeeRecord(AddEmployeeRecordVo addEmployeeRecordVo) {
        // 0. 将addEmployeeRecordVo转换为EmployeeRecord对象
        EmployeeRecord employeeRecord = employeeRecordMapping.addEmployeeRecordVoToEmployeeRecord(addEmployeeRecordVo);

        // 1. 将三个机构code转换为最终机构id
        Integer code1 = addEmployeeRecordVo.getOrganizationCode1();
        Integer code2 = addEmployeeRecordVo.getOrganizationCode2();
        Integer code3 = addEmployeeRecordVo.getOrganizationCode3();
        if (code1 == null || code2 == null || code3 == null) {
            throw new CustomException("机构代码不能为空");
        }
        if (code1 < 0 || code2 < 0 || code3 < 0 || code1 > 99 || code2 > 99 || code3 > 99) {
            throw new CustomException("机构代码错误");
        }
        Integer orgId = organizationService.getOrgByCode(code1, code2, code3);
        if (orgId == null) {
            throw new CustomException("机构代码错误");
        }
        employeeRecord.setOrganizationId(orgId);

        // 2. 根据规则生成档案编号
        // 创建StringBuilder对象
        StringBuilder sb = new StringBuilder();

        // 2024
        sb.append(String.format("%04d", LocalDate.now().getYear()));
        // 依次添加每个机构code，并确保它们都是两位数
        sb.append(String.format("%02d", code1));
        sb.append(String.format("%02d", code2));
        sb.append(String.format("%02d", code3));
        // 转换成最终的字符串
        String formattedCode = sb.toString();
        // 查询数据库中最大的档案编号
        List<String> maxRecordNum = baseMapper.getMaxRecordNum(formattedCode);
        int max = 0;
        for (String s : maxRecordNum) {
            max = Math.max(max, Integer.parseInt(s.substring(10)));
        }
        // 生成新的档案编号
        sb.append(String.format("%02d", max + 1));
        employeeRecord.setRecordNumber(sb.toString());

        // 3. 插入员工档案
        baseMapper.insert(employeeRecord);

        return employeeRecord;
    }
}




