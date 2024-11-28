package com.gdou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.pojo.entity.SalaryStandard;
import com.gdou.pojo.vo.salary.SalaryItemMoneyVo;
import com.gdou.pojo.vo.salary.SalaryStandardListVo;

import java.util.List;

/**
 * @author howe
 */
public interface SalaryStandardMapper extends BaseMapper<SalaryStandard> {

    List<SalaryItemMoneyVo> getSalaryItemMoneyList(Integer id);

    List<SalaryStandardListVo> getOneByEmpId(String empId);
}




