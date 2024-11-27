package com.gdou.pojo.vo.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author howe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeRecordVo {
    @Schema(description = "员工姓名")
    private String name;

    @Schema(description = "员工性别(男，女)")
    private String gender;

    @Schema(description = "员工出生日期")
    private Date birthDate;

    @Schema(description = "员工年龄")
    private Integer age;

    @Schema(description = "员工民族")
    private String ethnicity;

    @Schema(description = "员工宗教信仰")
    private String religion;

    @Schema(description = "员工政治面貌")
    private String politicalStatus;

    @Schema(description = "员工身份证号码")
    private String idCardNumber;

    @Schema(description = "社保号码")
    private String socialSecurityNumber;

    @Schema(description = "学历")
    private String education;

    @Schema(description = "学历对应的专业")
    private String educationMajor;

    @Schema(description = "员工国籍")
    private String nationality;

    @Schema(description = "员工出生地")
    private String birthplace;

    @Schema(description = "固定电话")
    private String phone;

    @Schema(description = "手机号码")
    private String mobile;

    @Schema(description = "邮箱地址")
    private String email;

    @Schema(description = "QQ号")
    private String qq;

    @Schema(description = "详细住址")
    private String address;

    @Schema(description = "邮政编码")
    private String postalCode;

    @Schema(description = "开户行")
    private String bank;

    @Schema(description = "银行账号")
    private String account;

    @Schema(description = "员工特长")
    private String specialty;

    @Schema(description = "员工爱好")
    private String hobbies;

    @Schema(description = "个人履历")
    private String resume;

    @Schema(description = "家庭关系信息")
    private String familyInfo;

    @Schema(description = "备注信息")
    private String remarks;

    @Schema(description = "登记人，外键，关联用户表")
    private Integer registrarId;

    @Schema(description = "一级机构code")
    private Integer organizationCode1;
    @Schema(description = "二级机构code")
    private Integer organizationCode2;
    @Schema(description = "三级机构code")
    private Integer organizationCode3;

    @Schema(description = "职位ID，外键，关联职位表")
    private Integer positionId;

    @Schema(description = "职称(初级，中级，高级)")
    private String title;

    @Schema(description = "薪酬标准ID，外键，关联薪酬标准表")
    private Integer salaryStandardId;

    @Schema(description = "照片URL路径")
    private String photoUrl;
}