package com.gdou.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.gdou.common.ResultCode;
import com.gdou.common.UserRole;
import com.gdou.pojo.vo.Result;
import com.gdou.pojo.vo.salary.AddSalaryStandardVo;
import com.gdou.pojo.vo.salary.ConditionSearchVo;
import com.gdou.pojo.vo.salary.RegisterSalaryStandardVo;
import com.gdou.pojo.vo.salary.ReviewSalaryStandardVo;
import com.gdou.service.SalaryStandardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * 根据薪酬标准的状态获取薪酬标准列表
     */
    @PostMapping("/list/{type}")
    @Operation(summary = "根据状态获取薪酬标准", description = "0->所有 1->待登记 2->待复核 3->正常")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "type", description = "表示薪酬状态，取值：0-4", in = ParameterIn.PATH, schema = @Schema(type = "integer"), required = true)
    })
    public Result listAvailable(@PathVariable Integer type) {
        String typeStr = "";
        if (type == 1) typeStr = "待登记";
        if (type == 2) typeStr = "待复核";
        if (type == 3) typeStr = "正常";
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("获取成功")
                .data(salaryStandardService.getList(typeStr))
                .build();
    }

    /**
     * 薪酬专员登记薪酬标准中的薪酬项目金额
     */
    @PostMapping("/register")
    @Operation(summary = "登记薪酬标准", description = "薪酬专员登记薪酬标准")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.SALARY_SPECIALIST_STR)
    public Result registerSalaryStandard(@RequestBody RegisterSalaryStandardVo rssv) {
        salaryStandardService.registerSalaryStandard(rssv);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("登记成功")
                .build();
    }

    /**
     * 薪酬经理复核薪酬标准中的薪酬项目金额
     */
    @PostMapping("/review")
    @Operation(summary = "复核薪酬标准", description = "薪酬经理复核薪酬标准")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.SALARY_MANAGER_STR)
    public Result reviewSalaryStandard(@RequestBody ReviewSalaryStandardVo rssv) {
        salaryStandardService.reviewSalaryStandard(rssv);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("复核成功")
                .build();
    }

    /**
     * 条件查询薪酬标准
     */
    @PostMapping("/search")
    @Operation(summary = "条件查询薪酬标准", description = "查询条件：薪酬标准编号、关键字和登记时间。薪酬标准编号和关键字支持模糊查询。关键字查询条件在薪酬标准名称、制定人、变更人和复核人字段进行匹配。登记时间查询条件包括起止时间，结束时间，在登记时间大于等于开始时间小于等于结束时间的记录进行匹配。")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.SALARY_SPECIALIST_STR, UserRole.SALARY_MANAGER_STR}, mode = SaMode.OR)
    public Result conditionSearch(@RequestBody ConditionSearchVo conditionSearchVo) {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("查询成功")
                .data(salaryStandardService.conditionSearch(conditionSearchVo))
                .build();
    }

    //TODO: 姓名 档案编号 职位名称 职称 薪酬标准名称(id)
}
