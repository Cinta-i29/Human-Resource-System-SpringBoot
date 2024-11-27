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
public class UpdateEmployeeRecordVo {
    /**
     * 档案编号，唯一标识
     */
    @Parameter(description = "档案编号，唯一标识")
    private String recordNumber;

    /**
     * 员工姓名
     */
    @Parameter(description = "员工姓名")
    private String name;

    /**
     * 员工性别
     */
    @Parameter(description = "员工性别")
    private String gender;

    /**
     * 员工出生日期
     */
    @Parameter(description = "员工出生日期")
    private Date birthDate;

    /**
     * 员工年龄
     */
    @Parameter(description = "员工年龄")
    private Integer age;

    /**
     * 员工民族
     */
    @Parameter(description = "员工民族")
    private String ethnicity;

    /**
     * 员工宗教信仰
     */
    @Parameter(description = "员工宗教信仰")
    private String religion;

    /**
     * 员工政治面貌
     */
    @Parameter(description = "员工政治面貌")
    private String politicalStatus;

    /**
     * 员工身份证号码
     */
    @Parameter(description = "员工身份证号码")
    private String idCardNumber;

    /**
     * 社保号码
     */
    @Parameter(description = "社保号码")
    private String socialSecurityNumber;

    /**
     * 学历
     */
    @Parameter(description = "学历")
    private String education;

    /**
     * 学历对应的专业
     */
    @Parameter(description = "学历对应的专业")
    private String educationMajor;

    /**
     * 员工国籍
     */
    @Parameter(description = "员工国籍")
    private String nationality;

    /**
     * 员工出生地
     */
    @Parameter(description = "员工出生地")
    private String birthplace;

    /**
     * 固定电话
     */
    @Parameter(description = "固定电话")
    private String phone;

    /**
     * 手机号码
     */
    @Parameter(description = "手机号码")
    private String mobile;

    /**
     * 邮箱地址
     */
    @Parameter(description = "邮箱地址")
    private String email;

    /**
     * QQ号
     */
    @Parameter(description = "QQ号")
    private String qq;

    /**
     * 详细住址
     */
    @Parameter(description = "详细住址")
    private String address;

    /**
     * 邮政编码
     */
    @Parameter(description = "邮政编码")
    private String postalCode;

    /**
     * 开户行
     */
    @Parameter(description = "开户行")
    private String bank;

    /**
     * 银行账号
     */
    @Parameter(description = "银行账号")
    private String account;

    /**
     * 员工特长
     */
    @Parameter(description = "员工特长")
    private String specialty;

    /**
     * 员工爱好
     */
    @Parameter(description = "员工爱好")
    private String hobbies;

    /**
     * 个人履历
     */
    @Parameter(description = "个人履历")
    private String resume;

    /**
     * 家庭关系信息
     */
    @Parameter(description = "家庭关系信息")
    private String familyInfo;

    /**
     * 备注信息
     */
    @Parameter(description = "备注信息")
    private String remarks;

    /**
     * 登记人，外键，关联用户表
     */
    @Parameter(description = "登记人，外键，关联用户表")
    private Integer registrarId;

    /**
     * 薪酬标准ID，外键，关联薪酬标准表
     */
    @Parameter(description = "薪酬标准ID，外键，关联薪酬标准表")
    private Integer salaryStandardId;
}
