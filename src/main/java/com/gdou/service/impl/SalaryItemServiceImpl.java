package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.exception.CustomException;
import com.gdou.mapper.SalaryItemMapper;
import com.gdou.pojo.entity.SalaryItem;
import com.gdou.pojo.vo.salary.AddSalaryItemVo;
import com.gdou.service.SalaryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author zzhave
 * @description 针对表【salary_item(薪酬项目表)】的数据库操作Service实现
 * @createDate 2024-11-25 23:23:57
 */
@Service
@RequiredArgsConstructor
public class SalaryItemServiceImpl extends ServiceImpl<SalaryItemMapper, SalaryItem>
        implements SalaryItemService {
    public void addSalaryItem(AddSalaryItemVo addSalaryItemVo) {
        // 1. 查询名称是否存在
        SalaryItem salaryItem = this.lambdaQuery()
                .eq(SalaryItem::getName, addSalaryItemVo.getName())
                .one();
        if (salaryItem != null) {
            throw new CustomException("薪酬项目名称已存在");
        }
        // 2. 新增
        salaryItem = new SalaryItem();
        salaryItem.setName(addSalaryItemVo.getName());
        salaryItem.setIsDeduction(addSalaryItemVo.getIsDeduction());
        this.save(salaryItem);
    }

    public void delete(Integer id) {
        // 1. 查询是否有关联
        if (id == 1) {
            throw new CustomException("基本工资项目不可删除");
        }
        Integer count = baseMapper.selectCountBySalaryItemId(id);
        if (count > 0) {
            throw new CustomException("薪酬项目已被使用，无法删除");
        }
        // 2. 删除
        this.removeById(id);
    }
}




