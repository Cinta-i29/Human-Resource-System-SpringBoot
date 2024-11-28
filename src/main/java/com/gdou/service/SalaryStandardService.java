package com.gdou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.pojo.entity.SalaryStandard;
import com.gdou.pojo.vo.salary.*;

import java.util.List;

/**
 * @author zzhave
 * @description 针对表【salary_standard(薪酬标准表)】的数据库操作Service
 * @createDate 2024-11-25 23:24:00
 */
public interface SalaryStandardService extends IService<SalaryStandard> {

    void addSalaryStandard(AddSalaryStandardVo addSalaryStandardVo);

    void registerSalaryStandard(RegisterSalaryStandardVo rssv);

    void reviewSalaryStandard(ReviewSalaryStandardVo rssv);

    List<SalaryStandardListVo> getList(String typeStr);

    List<SalaryStandardListVo> conditionSearch(ConditionSearchVo conditionSearchVo);
}
