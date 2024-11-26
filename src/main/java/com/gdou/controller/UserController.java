package com.gdou.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.gdou.common.ResultCode;
import com.gdou.common.UserRole;
import com.gdou.mapping.UserMapping;
import com.gdou.pojo.entity.User;
import com.gdou.pojo.vo.user.LoginUserVo;
import com.gdou.pojo.vo.Result;
import com.gdou.pojo.vo.user.UpdateUser;
import com.gdou.pojo.vo.user.UserLoginSuccessVo;
import com.gdou.service.UserService;
import com.gdou.support.LoginUserProvider;
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
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户登录模块")
public class UserController {

    private final UserService userService;
    private final UserMapping userMapping;
    private final LoginUserProvider loginUserProvider;

    /**
     * 用户登录接口
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录接口", description = "登录成功后会返回一个token，后续的请求都需要带上这个token")
    public Result login(@RequestBody LoginUserVo loginUserVo) {
        User user = userService.login(userMapping.loginUserVoToUser(loginUserVo));
        UserLoginSuccessVo userLoginSuccessVo = userMapping.userToUserLoginSuccessVo(user);
        if (StrUtil.isNotBlank(StpUtil.getTokenValueByLoginId(userLoginSuccessVo.getUserId()))) {
            StpUtil.logout(userLoginSuccessVo.getUserId());
        }
        StpUtil.login(userLoginSuccessVo.getUserId());
        userLoginSuccessVo.setToken(StpUtil.getTokenInfo().getTokenValue());

        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("登录成功")
                .data(userLoginSuccessVo)
                .build();
    }

    /**
     * 用户登出
     */
    @GetMapping("/logout")
    @Operation(summary = "用户登出", description = "用户登出")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result logout() {
        StpUtil.logout(loginUserProvider.getLoginUser().getUserId());
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("登出成功")
                .build();
    }

    /**
     * 获取所有用户
     */
    @GetMapping("/all")
    @Operation(summary = "获取所有用户", description = "获取所有用户")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR) // 只有超级管理员权限才能查看
    public Result getAll() {
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("获取成功")
                .data(userService.getAll())
                .build();
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/update")
    @Operation(summary = "修改用户信息", description = "修改用户信息")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR) // 只有超级管理员权限才能查看
    public Result update(@RequestBody UpdateUser user) {
        userService.updateById(userMapping.updateUserToUser(user));
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("修改成功")
                .build();
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/delete/{userId}")
    @Operation(summary = "删除用户", description = "删除用户")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR) // 只有超级管理员权限才能查看
    public Result delete(@PathVariable Integer userId) {
        userService.removeById(userId);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("删除成功")
                .build();
    }

    /**
     * 新增用户
     */
    @PostMapping("/add")
    @Operation(summary = "新增用户", description = "新增用户")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    @SaCheckRole(UserRole.ADMIN_STR) // 只有超级管理员权限才能查看
    public Result add(@RequestBody User user) {
        userService.save(user);
        return Result.builder()
                .code(ResultCode.SUCCESS.code)
                .msg("新增成功")
                .build();
    }
}
