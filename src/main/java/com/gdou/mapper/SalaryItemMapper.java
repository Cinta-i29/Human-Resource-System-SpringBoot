package com.gdou.mapper;

import com.gdou.pojo.entity.SalaryItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zzhave
* @description 针对表【salary_item(薪酬项目表)】的数据库操作Mapper
* @createDate 2024-11-25 23:23:57
* @Entity com.gdou.pojo.entity.SalaryItem
*/
@Mapper
public interface SalaryItemMapper extends BaseMapper<SalaryItem> {

    Integer selectCountBySalaryItemId(Integer id);
}




