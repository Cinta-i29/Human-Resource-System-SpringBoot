package com.gdou.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.gdou.common.ResultCode;
import com.gdou.common.UserRole;
import com.gdou.pojo.vo.Result;
import com.gdou.pojo.vo.bill.ConditionalSearchBillEmpVo;
import com.gdou.pojo.vo.bill.RegisterBillVo;
import com.gdou.pojo.vo.bill.ReviewBillVo;
import com.gdou.service.BillService;
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
@RequestMapping("/bill")
@RequiredArgsConstructor
@Tag(name = "薪酬账单管理模块")
public class BillController {
    private final BillService billService;

    /**
     * 返回可以申请薪酬账单的档案列表
     */
    @GetMapping("/employee/list")
    @Operation(summary = "返回可以登记薪酬账单的档案列表", description = "返回可以申请薪酬账单的档案列表：1. 档案状态为'正常' 2. 薪酬标准ID不为空 3. 薪酬标准的状态为'正常'")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.SALARY_SPECIALIST_STR, UserRole.SALARY_MANAGER_STR}, mode = SaMode.OR)
    public Result getEmpList(@RequestBody ConditionalSearchBillEmpVo conditionalSearchBillEmpVo) {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("查询成功")
                .data(billService.getEmpList(conditionalSearchBillEmpVo))
                .build();
    }

//    /**
//     * 获取薪酬账单详情
//     */
//    @GetMapping("/detail/{billNumber}")
//    @Operation(summary = "获取薪酬账单详情", description = "获取薪酬账单详情")
//    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
//    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.SALARY_SPECIALIST_STR, UserRole.SALARY_MANAGER_STR}, mode = SaMode.OR)
//    public Result getBillDetail(@PathVariable Integer billNumber) {
//        return Result.builder()
//                .code(ResultCode.SUCCESS.code)
//                .msg("查询成功")
//                .data(billService.getBillDetail(billNumber))
//                .build();
//    }


    /**
     * 查询账单列表(可选择查询条件)(薪酬单号、关键字和发放时间)
     * TODO: Implement this method
     */
//    @GetMapping("/list")
//    @Operation(summary = "查询账单列表", description = "查询账单列表(可选择查询条件)(薪酬单号、关键字和发放时间)")
//    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
//    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.SALARY_SPECIALIST_STR, UserRole.SALARY_MANAGER_STR}, mode = SaMode.OR)
//    public Result getBillList(@RequestBody ConditionalSearchBillVo conditionalSearchBillVo) {
//        return Result.builder()
//                .code(ResultCode.SUCCESS.code)
//                .msg("查询成功")
//                .data(billService.getBillList(conditionalSearchBillVo))
//                .build();
//    }

    /**
     * 薪酬专员登记某个档案的薪酬账单
     */
    @PutMapping("/register")
    @Operation(summary = "薪酬专员登记某个档案的薪酬账单", description = "薪酬专员登记某个档案的薪酬账单")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.SALARY_SPECIALIST_STR)
    public Result addBill(@RequestBody RegisterBillVo registerBillVo) {
        billService.registerBill(registerBillVo);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("登记成功")
                .build();
    }

    /**
     * 薪酬专员复核某个档案的薪酬账单
     */
    @PutMapping("/review")
    @Operation(summary = "薪酬专员复核某个档案的薪酬账单", description = "薪酬专员复核某个档案的薪酬账单")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.SALARY_MANAGER_STR)
    public Result reviewBill(@RequestBody ReviewBillVo reviewBillVo) {
        billService.reviewBill(reviewBillVo);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("复核成功")
                .build();
    }
}
