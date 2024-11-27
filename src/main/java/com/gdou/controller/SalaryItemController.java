package com.gdou.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.gdou.common.ResultCode;
import com.gdou.common.UserRole;
import com.gdou.pojo.vo.salary.AddSalaryItemVo;
import com.gdou.pojo.vo.Result;
import com.gdou.service.SalaryItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/salaryItem")
@Tag(name = "薪酬项目管理模块")
public class SalaryItemController {
    private final SalaryItemService salaryItemService;

    /**
     * 新增薪酬项目
     */
    @PostMapping("/add")
    @Operation(summary = "新增薪酬项目", description = "新增薪酬项目")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR)
    public Result add(@RequestBody AddSalaryItemVo addSalaryItemVo) {
        salaryItemService.addSalaryItem(addSalaryItemVo);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("新增成功")
                .build();
    }

    /**
     * 获取所有薪酬项目
     */
    @PostMapping("/list")
    @Operation(summary = "获取所有薪酬项目", description = "")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR)
    public Result list() {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("获取成功")
                .data(salaryItemService.list())
                .build();
    }

    /**
     * 删除薪酬项目
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "删除薪酬项目", description = "id为1的基本不可以删除；其他的当没被使用的时候可删除")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR)
    public Result delete(@PathVariable Integer id) {
        salaryItemService.delete(id);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("删除成功")
                .build();
    }
}
