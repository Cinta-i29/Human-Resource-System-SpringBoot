package com.gdou.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.gdou.common.ResultCode;
import com.gdou.common.UserRole;
import com.gdou.mapping.EmployeeRecordMapping;
import com.gdou.pojo.vo.Result;
import com.gdou.pojo.vo.employee.AddEmployeeRecordVo;
import com.gdou.pojo.vo.employee.ConditionalQueriesEmployeeVo;
import com.gdou.pojo.vo.employee.UpdateEmployeeRecordVo;
import com.gdou.pojo.vo.employee.UpdateEmployeeRecordVoFromHrmVo;
import com.gdou.service.EmployeeRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author howe
 */
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Tag(name = "员工档案管理模块")
public class EmployeeRecordController {
    private final EmployeeRecordService employeeRecordService;
    private final EmployeeRecordMapping employeeRecordMapping;

    /**
     * 新增员工档案
     */
    @PostMapping("/add")
    @Operation(summary = "新增员工档案", description = "新增员工档案，返回新增的员工档案信息。然后再根据员工档案号上传新的员工信息")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.HR_SPECIALIST_STR, UserRole.HR_MANAGER_STR}, mode = SaMode.OR)
    public Result add(@RequestBody AddEmployeeRecordVo addEmployeeRecordVo) {
        return Result.builder()
                .data(employeeRecordService.addEmployeeRecord(addEmployeeRecordVo).getRecordNumber())
                .code(ResultCode.SUCCESS.code)
                .msg("新增成功")
                .build();
    }

    /**
     * 人事专员修改员工档案
     */
    @PostMapping("/update/s")
    @Operation(summary = "人事专员修改员工档案", description = "人事专员修改员工档案")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.HR_SPECIALIST_STR)
    public Result updateFromHrs(@RequestBody UpdateEmployeeRecordVo updateEmployeeRecordVo) {
        employeeRecordService.updateEmployeeRecordFromHrs(employeeRecordMapping.updateEmployeeRecordVoToEmployeeRecord(updateEmployeeRecordVo));
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("修改成功")
                .build();
    }

    /**
     * 人事经理修改(复核)员工档案
     */
    @PostMapping("/update/m")
    @Operation(summary = "人事经理修改(复核)员工档案", description = "人事经理修改员工档案,或者复核人事专员修改员工档案。多了一个复核意见")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.HR_MANAGER_STR)
    public Result updateFromHrm(@RequestBody UpdateEmployeeRecordVoFromHrmVo updateEmployeeRecordVoFromHrmVo) {
        employeeRecordService.updateEmployeeRecordFromHrm(updateEmployeeRecordVoFromHrmVo);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("修改成功")
                .build();
    }

    /**
     * 根据档案号查询员工档案
     */
    @PostMapping("/get//{recordNumber}")
    @Operation(summary = "根据档案号查询员工档案", description = "根据档案号查询员工档案，查不到已删除的")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.HR_SPECIALIST_STR, UserRole.HR_MANAGER_STR}, mode = SaMode.OR)
    public Result get(@PathVariable String recordNumber) {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("查询成功")
                .data(employeeRecordService.getEmployeeRecord(recordNumber))
                .build();
    }

    /**
     * 获取所有员工档案
     */
    @PostMapping("/list")
    @Operation(summary = "获取所有员工档案", description = "获取所有员工档案，不包括'已删除'的档案")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.HR_SPECIALIST_STR, UserRole.HR_MANAGER_STR}, mode = SaMode.OR)
    public Result list() {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("获取成功")
                .data(employeeRecordService.getList())
                .build();
    }

    /**
     * 获取已删除的员工档案
     */
    @PostMapping("/list/deleted")
    @Operation(summary = "获取已删除的员工档案", description = "获取已删除的员工档案，得到的只有'已删除'的档案")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.HR_MANAGER_STR}, mode = SaMode.OR)
    public Result listDeleted() {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("获取成功")
                .data(employeeRecordService.getListDeleted())
                .build();
    }

    /**
     * 删除员工档案
     */
    @PostMapping("/delete/{recordNumber}")
    @Operation(summary = "删除员工档案", description = "删除员工档案")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.HR_MANAGER_STR}, mode = SaMode.OR)
    public Result delete(@PathVariable String recordNumber) {
        employeeRecordService.deleteEmployeeRecord(recordNumber);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("删除成功")
                .build();
    }

    /**
     * 恢复员工档案
     */
    @PostMapping("/recover/{recordNumber}")
    @Operation(summary = "恢复员工档案", description = "恢复员工档案，只有'已删除'的档案才能恢复")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.HR_MANAGER_STR}, mode = SaMode.OR)
    public Result recover(@PathVariable String recordNumber) {
        employeeRecordService.recoverEmployeeRecord(recordNumber);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("恢复成功")
                .build();
    }

    /**
     * 根据员工档案编号上传头像
     */
    @PostMapping("/upload/avatar/{recordNumber}")
    @Operation(summary = "根据员工档案编号上传头像", description = "根据员工档案编号上传头像，返回上传的头像地址。头像地址命名是 档案编号+文件类型的形式")
    @Parameters({
            @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true),
            @Parameter(name = "headPhoto", description = "头像", in = ParameterIn.QUERY, schema = @Schema(type = "file"), required = true)
    })
    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.HR_SPECIALIST_STR, UserRole.HR_MANAGER_STR}, mode = SaMode.OR)
    public Result uploadAvatar(@PathVariable String recordNumber, @RequestParam("headPhoto") MultipartFile file) {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("上传成功")
                .data(employeeRecordService.uploadAvatar(recordNumber, file))
                .build();
    }

    /**
     * 条件查询员工
     */
    @PostMapping("/condition/search")
    @Operation(summary = "条件查询员工", description = "条件查询员工，返回符合条件的员工档案列表")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(value = {UserRole.ADMIN_STR, UserRole.HR_SPECIALIST_STR, UserRole.HR_MANAGER_STR}, mode = SaMode.OR)
    public Result search(@RequestBody ConditionalQueriesEmployeeVo conditionalQueriesEmployeeVo) {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("查询成功")
                .data(employeeRecordService.conditionSearch(conditionalQueriesEmployeeVo))
                .build();
    }

}

