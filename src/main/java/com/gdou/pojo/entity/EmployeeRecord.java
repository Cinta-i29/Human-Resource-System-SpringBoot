package com.gdou.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工档案表
 * @TableName employee_record
 */
@TableName(value ="employee_record")
@Data
public class EmployeeRecord implements Serializable {
    /**
     * 档案编号，唯一标识
     */
    private String recordNumber;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工性别
     */
    private String gender;

    /**
     * 员工出生日期
     */
    private Date birthDate;

    /**
     * 员工年龄
     */
    private Integer age;

    /**
     * 员工民族
     */
    private String ethnicity;

    /**
     * 员工宗教信仰
     */
    private String religion;

    /**
     * 员工政治面貌
     */
    private String politicalStatus;

    /**
     * 员工身份证号码
     */
    private String idCardNumber;

    /**
     * 社保号码
     */
    private String socialSecurityNumber;

    /**
     * 学历
     */
    private String education;

    /**
     * 学历对应的专业
     */
    private String educationMajor;

    /**
     * 员工国籍
     */
    private String nationality;

    /**
     * 员工出生地
     */
    private String birthplace;

    /**
     * 固定电话
     */
    private String phone;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 详细住址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 开户行
     */
    private String bank;

    /**
     * 银行账号
     */
    private String account;

    /**
     * 员工特长
     */
    private String specialty;

    /**
     * 员工爱好
     */
    private String hobbies;

    /**
     * 个人履历
     */
    private String resume;

    /**
     * 家庭关系信息
     */
    private String familyInfo;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 登记人，外键，关联用户表
     */
    private Integer registrarId;

    /**
     * 登记时间
     */
    private Date registrationTime;

    /**
     * 所属机构ID，外键，关联机构表
     */
    private Integer organizationId;

    /**
     * 职位ID，外键，关联职位表
     */
    private Integer positionId;

    /**
     * 职称
     */
    private String title;

    /**
     * 薪酬标准ID，外键，关联薪酬标准表
     */
    private Integer salaryStandardId;

    /**
     * 奖励奖金
     */
    private Integer rewardBonus;

    /**
     * 应扣奖金
     */
    private Integer deductionBonus;

    /**
     * 照片URL路径
     */
    private String photoUrl;

    /**
     * 档案状态
     */
    private String status;

}