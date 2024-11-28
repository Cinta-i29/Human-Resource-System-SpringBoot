package com.gdou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.pojo.entity.SalaryItem;

/**
 * @author howe
 */
public interface SalaryItemMapper extends BaseMapper<SalaryItem> {

    Integer selectCountBySalaryItemId(Integer id);
}




