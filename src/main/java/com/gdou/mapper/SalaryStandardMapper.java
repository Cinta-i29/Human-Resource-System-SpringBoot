package com.gdou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.pojo.entity.SalaryStandard;
import com.gdou.pojo.vo.salary.SalaryItemMoneyVo;

import java.util.List;

/**
 * @author howe
 */
public interface SalaryStandardMapper extends BaseMapper<SalaryStandard> {

    List<SalaryItemMoneyVo> getSalaryItemMoneyList(Integer id);
}




