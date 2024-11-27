show databases;

create database hrs;

use hrs;

CREATE TABLE organization
(
    id         INT PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '机构编号，主键',                           -- 机构编号，主键
    name       VARCHAR(255)                   NOT NULL COMMENT '机构名称',                                -- 机构名称
    parent_id  INT COMMENT '父级机构id',                                                                  -- 父级机构id
    code       INT                            NOT NULL COMMENT '机构code',                                -- 机构code
    level      INT                            NOT NULL COMMENT '机构层级，用于快速判断机构之间的层级关系', -- 机构层级，用于快速判断机构之间的层级关系
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',                                     -- 创建时间
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',         -- 更新时间
    CHECK (level IN (1, 2, 3))                                                                            -- 机构层级的检查（如：1为一级，2为二级，3为三级）
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '机构表';

CREATE TABLE bill
(
    salary_number INT COMMENT '薪酬编号',                                                              -- 薪酬编号
    org_id        INT COMMENT '机构id',                                                                -- 机构id
    month         VARCHAR(50) COMMENT '薪酬月份',                                                      -- 薪酬月份
    data          JSON COMMENT '薪酬数据',                                                             -- 薪酬数据
    status        VARCHAR(50) DEFAULT '待复核' CHECK (status IN ('待复核', '已发放')) COMMENT '状态',  -- 状态
    created_at    DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',                            -- 创建时间
    updated_at    DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' -- 更新时间
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '账单薪酬月份表';

CREATE TABLE position
(
    id         INT AUTO_INCREMENT PRIMARY KEY,                                -- 职位的唯一标识
    name       VARCHAR(255) NOT NULL,                                         -- 职位名称
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,                            -- 创建时间
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 更新时间
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '职位表';

CREATE TABLE salary_standard
(
    id           INT AUTO_INCREMENT PRIMARY KEY,                                              -- 薪酬标准的唯一标识
    name         VARCHAR(255) NOT NULL,                                                       -- 薪酬标准名称
    creator_id   INT          NOT NULL,                                                       -- 制定人id
    registrar_id INT          NOT NULL,                                                       -- 登记人id
    status       VARCHAR(50) DEFAULT '待登记' CHECK (status IN ('待登记', '待复核', '正常')), -- 状态
    created_at   DATETIME    DEFAULT CURRENT_TIMESTAMP,                                       -- 登记时间
    comment      TEXT,                                                                        -- 复核意见
    updated_at   DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP            -- 更新时间
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '薪酬标准表';

CREATE TABLE salary_item
(
    id         INT AUTO_INCREMENT PRIMARY KEY,    -- 薪酬项目的唯一标识
    name       VARCHAR(255) NOT NULL,             -- 薪酬项目名称
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP -- 创建时间
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '薪酬项目表';

CREATE TABLE salary_standard_item
(
    salary_standard_id INT            NOT NULL,            -- 所属薪酬标准
    salary_item_id     INT            NOT NULL,            -- 对应的薪酬项目
    amount             DECIMAL(10, 2) NOT NULL,            -- 该项目对应的金额
    created_at         DATETIME DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    PRIMARY KEY (salary_standard_id, salary_item_id)       -- 唯一组合索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '薪酬标准与薪酬项目关系表';

CREATE TABLE user
(
    user_id    INT AUTO_INCREMENT PRIMARY KEY,                                -- 用户ID，主键
    username   VARCHAR(50)  NOT NULL,                                         -- 用户名
    password   VARCHAR(255) NOT NULL,                                         -- 用户密码
    name       VARCHAR(50)  NOT NULL,                                         -- 用户登录名
    role       VARCHAR(50)  NOT NULL CHECK (role IN
                                            ('Administrator', 'HR-Specialist', 'HR-Manager', 'Salary-Specialist',
                                             'Salary-Manager')),              -- 用户角色
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,                            -- 创建时间
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 更新时间
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户信息表';

CREATE TABLE employee_record
(
    record_number          VARCHAR(14) PRIMARY KEY,                                                     -- 档案编号，唯一标识
    name                   VARCHAR(50) NOT NULL,                                                        -- 员工姓名
    gender                 VARCHAR(10) CHECK (gender IN ('男', '女')),                                  -- 员工性别
    birth_date             DATE,                                                                        -- 员工出生日期
    age                    INT,                                                                         -- 员工年龄
    ethnicity              VARCHAR(20),                                                                 -- 员工民族
    religion               VARCHAR(50),                                                                 -- 员工宗教信仰
    political_status       VARCHAR(50),                                                                 -- 员工政治面貌
    id_card_number         VARCHAR(18),                                                                 -- 员工身份证号码
    social_security_number VARCHAR(20),                                                                 -- 社保号码
    education              VARCHAR(20),                                                                 -- 学历
    education_major        VARCHAR(50),                                                                 -- 学历对应的专业
    nationality            VARCHAR(20),                                                                 -- 员工国籍
    birthplace             VARCHAR(50),                                                                 -- 员工出生地
    phone                  VARCHAR(20),                                                                 -- 固定电话
    mobile                 VARCHAR(20),                                                                 -- 手机号码
    email                  VARCHAR(50),                                                                 -- 邮箱地址
    qq                     VARCHAR(20),                                                                 -- QQ号
    address                VARCHAR(100),                                                                -- 详细住址
    postal_code            VARCHAR(10),                                                                 -- 邮政编码
    bank                   VARCHAR(50),                                                                 -- 开户行
    account                VARCHAR(50),                                                                 -- 银行账号
    specialty              VARCHAR(100),                                                                -- 员工特长
    hobbies                VARCHAR(100),                                                                -- 员工爱好
    resume                 TEXT,                                                                        -- 个人履历
    family_info            TEXT,                                                                        -- 家庭关系信息
    remarks                TEXT,                                                                        -- 备注信息
    registrar_id           INT,                                                                         -- 登记人，外键，关联用户表
    registration_time      DATETIME    DEFAULT CURRENT_TIMESTAMP,                                       -- 登记时间
    organization_id        INT,                                                                         -- 所属机构ID，外键，关联机构表
    position_id            INT,                                                                         -- 职位ID，外键，关联职位表
    title                  VARCHAR(50) DEFAULT '初级' CHECK (title IN ('高级', '中级', '初级')),        -- 职称
    salary_standard_id     INT,                                                                         -- 薪酬标准ID，外键，关联薪酬标准表
    reward_bonus           INT         DEFAULT 0,                                                       -- 奖励奖金
    deduction_bonus        INT         DEFAULT 0,                                                       -- 应扣奖金
    photo_url              VARCHAR(255),                                                                -- 照片URL路径
    status                 VARCHAR(50) DEFAULT '待复核' CHECK (status IN ('待复核', '正常', '已删除')), -- 档案状态
    review_opinions        TEXT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '员工档案表';