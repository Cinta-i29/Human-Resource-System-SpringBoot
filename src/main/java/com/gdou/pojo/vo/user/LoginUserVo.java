package com.gdou.pojo.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo {
    @Schema(description = "账号")
    @NotBlank
    private String username;

    @Schema(description = "密码")
    @NotBlank
    private String password;
}
