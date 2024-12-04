package com.gdou.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.gdou.common.ResultCode;
import com.gdou.common.UserRole;
import com.gdou.pojo.vo.Result;
import com.gdou.pojo.vo.org.AddOrgVo;
import com.gdou.service.OrganizationService;
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
@RequestMapping("/org")
@RequiredArgsConstructor
@Tag(name = "机构管理模块")
public class OrganizationController {
    private final OrganizationService organizationService;

    /**
     * 新增机构
     */
    @PostMapping("/add")
    @Operation(summary = "新增机构", description = "新增机构")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR)
    public Result add(@RequestBody AddOrgVo addOrgVo) {
        organizationService.addOrganization(addOrgVo);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("新增成功")
                .build();
    }

    /**
     * 获取所有机构
     */
    @PostMapping("/list")
    @Operation(summary = "获取所有机构", description = "获取所有机构，返回的是List<VO1>, VO1其中一个属性是List<VO2>, VO2其中一个属性是List<VO3>")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result list() {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("获取成功")
                .data(organizationService.getOrgList())
                .build();
    }
}
