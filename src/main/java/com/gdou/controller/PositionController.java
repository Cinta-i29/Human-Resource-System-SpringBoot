package com.gdou.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.gdou.common.ResultCode;
import com.gdou.common.UserRole;
import com.gdou.pojo.vo.Result;
import com.gdou.pojo.vo.position.AddPositionVo;
import com.gdou.pojo.vo.position.UpdatePositionVo;
import com.gdou.service.PositionService;
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
@RequestMapping("/position")
@Tag(name = "职位管理模块")
public class PositionController {
    private final PositionService positionService;

    /**
     * 新增职位
     */
    @PostMapping("/add")
    @Operation(summary = "新增职位", description = "当姓名存在时，也会新增失败")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR)
    public Result add(@RequestBody AddPositionVo addPositionVo) {
        return Result.builder()
                .data(positionService.addPosition(addPositionVo))
                .code(ResultCode.SUCCESS.code)
                .msg("新增成功")
                .build();
    }

    /**
     * 获取所有职位
     */
    @PostMapping("/list")
    @Operation(summary = "获取所有职位", description = "")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR)
    public Result list() {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("获取成功")
                .data(positionService.list())
                .build();
    }

    /**
     * 删除职位
     */
    @DeleteMapping("/{positionId}")
    @Operation(summary = "删除职位", description = "当没有员工使用该职位的时候，才能够被删除")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR)
    public Result delete(@PathVariable Long positionId) {
        positionService.deletePosition(positionId);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("删除成功")
                .build();
    }

    /**
     * 修改职位
     */
    @PutMapping("/update")
    @Operation(summary = "修改职位", description = "只能修改职位名称，不能修改ID")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR)
    public Result update(@RequestBody UpdatePositionVo updatePositionVo) {
        return Result.builder()
                .data(positionService.updatePosition(updatePositionVo))
                .code(ResultCode.SUCCESS.code)
                .msg("修改成功")
                .build();
    }
}


