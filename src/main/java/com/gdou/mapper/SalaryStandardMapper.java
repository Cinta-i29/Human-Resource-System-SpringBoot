package com.gdou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.pojo.entity.SalaryStandard;
import com.gdou.pojo.vo.salary.SalaryItemMoneyVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author zzhave
* @description 针对表【salary_standard(薪酬标准表)】的数据库操作Mapper
* @createDate 2024-11-25 23:24:00
* @Entity com.gdou.pojo.entity.SalaryStandard
*/
@Mapper
public interface SalaryStandardMapper extends BaseMapper<SalaryStandard> {

    List<SalaryItemMoneyVo> getSalaryItemMoneyList(Integer id);
}




