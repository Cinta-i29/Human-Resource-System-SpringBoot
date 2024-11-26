package com.gdou.controller;

import com.gdou.common.ResultCode;
import com.gdou.pojo.vo.Result;
import com.gdou.pojo.vo.employee.AddEmployeeRecordVo;
import com.gdou.service.EmployeeRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author howe
 */
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Tag(name = "员工档案管理模块")
public class EmployeeRecordController {
    private final EmployeeRecordService employeeRecordService;

    /**
     * 新增员工档案
     */
    @PostMapping("/add")
    public Result add(@RequestBody AddEmployeeRecordVo addEmployeeRecordVo) {
        return Result.builder()
                .data(employeeRecordService.addEmployeeRecord(addEmployeeRecordVo))
                .code(ResultCode.SUCCESS.code)
                .msg("新增成功")
                .build();
    }

}
