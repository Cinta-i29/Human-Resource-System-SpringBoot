package com.gdou.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.exception.CustomException;
import com.gdou.mapper.SalaryStandardMapper;
import com.gdou.mapping.SalaryStandardMapping;
import com.gdou.pojo.entity.SalaryStandard;
import com.gdou.pojo.entity.SalaryStandardItem;
import com.gdou.pojo.vo.salary.AddSalaryStandardVo;
import com.gdou.pojo.vo.salary.SalaryStandardListVo;
import com.gdou.service.SalaryStandardItemService;
import com.gdou.service.SalaryStandardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzhave
 * @description 针对表【salary_standard(薪酬标准表)】的数据库操作Service实现
 * @createDate 2024-11-25 23:24:00
 */
@Service
@RequiredArgsConstructor
public class SalaryStandardServiceImpl extends ServiceImpl<SalaryStandardMapper, SalaryStandard>
        implements SalaryStandardService {

    private final SalaryStandardMapping salaryStandardMapping;
    private final SalaryStandardItemService salaryStandardItemService;

    public void addSalaryStandard(AddSalaryStandardVo addSalaryStandardVo) {
        // 1. 查询名称是否存在
        SalaryStandard dbSalaryStandard = this.lambdaQuery()
                .eq(SalaryStandard::getName, addSalaryStandardVo.getName())
                .one();
        if (dbSalaryStandard != null) {
            throw new CustomException("薪酬标准名称已存在");
        }

        // 2. 新增
        SalaryStandard salaryStandard = salaryStandardMapping.addSalaryStandardVoToSalaryStandard(addSalaryStandardVo);
        salaryStandard.setCreatorId(StpUtil.getLoginIdAsInt()); // 设置制定者ID
        baseMapper.insert(salaryStandard);

        // 确保ID已被设置
        if (salaryStandard.getId() == null) {
            throw new CustomException("无法获取新添加的薪酬标准ID");
        }

        // 3. 添加基本工资，并且关联到薪酬标准
        List<Integer> tempList = addSalaryStandardVo.getSalaryItemIdList();
        tempList.add(1);
        List<Integer> list = tempList.stream().distinct().toList();

        List<SalaryStandardItem> salaryStandardItemList = new ArrayList<>();
        for (Integer id : list) {
            SalaryStandardItem salaryStandardItem = new SalaryStandardItem();
            salaryStandardItem.setSalaryStandardId(salaryStandard.getId());
            salaryStandardItem.setSalaryItemId(id);
            salaryStandardItemList.add(salaryStandardItem);
        }
        salaryStandardItemService.saveBatch(salaryStandardItemList);
    }

    public List<SalaryStandardListVo> getlist() {
        List<SalaryStandardListVo> list = salaryStandardMapping.salaryStandardToSalaryStandardListVo(baseMapper.selectList(null));
        for (SalaryStandardListVo vo : list) {
            vo.setSalaryItemMoneyVoList(baseMapper.getSalaryItemMoneyList(vo.getId()));
        }
        return list;
    }

}




