package com.gdou.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.gdou.common.ResultCode;
import com.gdou.common.UserRole;
import com.gdou.pojo.vo.Result;
import com.gdou.pojo.vo.salary.AddSalaryStandardVo;
import com.gdou.service.SalaryStandardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequiredArgsConstructor
@RequestMapping("/salaryStandard")
@Tag(name = "薪酬标准管理模块")
public class SalaryStandardController {
    private final SalaryStandardService salaryStandardService;

    /**
     * 超级管理员制定薪酬标准
     */
    @PostMapping("/add")
    @Operation(summary = "新增薪酬标准", description = "新增薪酬标准，此接口只有超级管理员能够使用")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR)
    public Result add(@RequestBody AddSalaryStandardVo addSalaryStandardVo) {
        salaryStandardService.addSalaryStandard(addSalaryStandardVo);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("新增成功")
                .build();
    }

    /**
     * 获取所有薪酬标准
     */
    @PostMapping("/list")
    @Operation(summary = "获取所有薪酬标准", description = "获取所有薪酬标准，此接口只有超级管理员能够使用")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.SALARY_MANAGER_STR}, mode = SaMode.OR)
    public Result list() {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("获取成功")
                .data(salaryStandardService.getlist())
                .build();
    }

//    /**
//     * 薪酬专员登记薪酬标准中的薪酬项目金额
//     */

//    /**
//     * 薪酬经理复核薪酬标准中的薪酬项目金额
//     */


}
