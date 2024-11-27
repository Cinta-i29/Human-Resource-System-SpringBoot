package com.gdou.pojo.vo.employee;

import io.swagger.v3.oas.annotations.Parameter;
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
    @Parameter(description = "员工姓名")
    private String name;

    @Parameter(description = "员工性别(男，女)")
    private String gender;

    @Parameter(description = "员工出生日期")
    private Date birthDate;

    @Parameter(description = "员工年龄")
    private Integer age;

    @Parameter(description = "员工民族")
    private String ethnicity;

    @Parameter(description = "员工宗教信仰")
    private String religion;

    @Parameter(description = "员工政治面貌")
    private String politicalStatus;

    @Parameter(description = "员工身份证号码")
    private String idCardNumber;

    @Parameter(description = "社保号码")
    private String socialSecurityNumber;

    @Parameter(description = "学历")
    private String education;

    @Parameter(description = "学历对应的专业")
    private String educationMajor;

    @Parameter(description = "员工国籍")
    private String nationality;

    @Parameter(description = "员工出生地")
    private String birthplace;

    @Parameter(description = "固定电话")
    private String phone;

    @Parameter(description = "手机号码")
    private String mobile;

    @Parameter(description = "邮箱地址")
    private String email;

    @Parameter(description = "QQ号")
    private String qq;

    @Parameter(description = "详细住址")
    private String address;

    @Parameter(description = "邮政编码")
    private String postalCode;

    @Parameter(description = "开户行")
    private String bank;

    @Parameter(description = "银行账号")
    private String account;

    @Parameter(description = "员工特长")
    private String specialty;

    @Parameter(description = "员工爱好")
    private String hobbies;

    @Parameter(description = "个人履历")
    private String resume;

    @Parameter(description = "家庭关系信息")
    private String familyInfo;

    @Parameter(description = "备注信息")
    private String remarks;

    @Parameter(description = "登记人，外键，关联用户表")
    private Integer registrarId;

    @Parameter(description = "一级机构code")
    private Integer organizationCode1;
    @Parameter(description = "二级机构code")
    private Integer organizationCode2;
    @Parameter(description = "三级机构code")
    private Integer organizationCode3;

    @Parameter(description = "职位ID，外键，关联职位表")
    private Integer positionId;

    @Parameter(description = "职称(初级，中级，高级)")
    private String title;

    @Parameter(description = "薪酬标准ID，外键，关联薪酬标准表")
    private Integer salaryStandardId;

    @Parameter(description = "照片URL路径")
    private String photoUrl;
}