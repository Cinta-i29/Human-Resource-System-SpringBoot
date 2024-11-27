package com.gdou.mapping;

import com.gdou.pojo.entity.SalaryStandard;
import com.gdou.pojo.vo.salary.AddSalaryStandardVo;
import com.gdou.pojo.vo.salary.SalaryStandardListVo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author howe
 */
@Mapper(componentModel = "spring")
public interface SalaryStandardMapping {
    SalaryStandard addSalaryStandardVoToSalaryStandard(AddSalaryStandardVo addSalaryStandardVo);

    List<SalaryStandardListVo> salaryStandardToSalaryStandardListVo(List<SalaryStandard> salaryStandard);
}
