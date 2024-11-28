package com.gdou.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.exception.CustomException;
import com.gdou.mapper.SalaryStandardMapper;
import com.gdou.mapping.SalaryStandardMapping;
import com.gdou.pojo.entity.SalaryStandard;
import com.gdou.pojo.entity.SalaryStandardItem;
import com.gdou.pojo.vo.salary.*;
import com.gdou.service.EmployeeRecordService;
import com.gdou.service.SalaryStandardItemService;
import com.gdou.service.SalaryStandardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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
    private final EmployeeRecordService employeeRecordService;

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

    public void registerSalaryStandard(RegisterSalaryStandardVo rssv) {
        // 1. 查询薪酬标准是否存在
        SalaryStandard dbSalaryStandard = this.lambdaQuery()
                .eq(SalaryStandard::getId, rssv.getId())
                .one();
        if (dbSalaryStandard == null) {
            throw new CustomException("薪酬标准不存在");
        }

        // 2. 设置薪酬项目对应的金额
        List<RegisterSalaryItemMoneyVo> moneyVoList = rssv.getRegisterSalaryItemMoneyVoList();
        for (RegisterSalaryItemMoneyVo vo : moneyVoList) {
            //  组装数据库对象
            SalaryStandardItem salaryStandardItem = new SalaryStandardItem();
            salaryStandardItem.setSalaryStandardId(rssv.getId());
            salaryStandardItem.setSalaryItemId(vo.getId());
            salaryStandardItem.setMoney(vo.getMoney());

            // 条件修改
            LambdaQueryWrapper<SalaryStandardItem> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SalaryStandardItem::getSalaryStandardId, rssv.getId());
            queryWrapper.eq(SalaryStandardItem::getSalaryItemId, vo.getId());
            salaryStandardItemService.update(salaryStandardItem, queryWrapper);
        }

        // 3. 将薪酬项目的状态设置为'待复核'
        dbSalaryStandard.setStatus("待复核");
        dbSalaryStandard.setRegistrarId(StpUtil.getLoginIdAsInt());
        dbSalaryStandard.setRegistrarAt(Calendar.getInstance().getTime());
        baseMapper.updateById(dbSalaryStandard);
    }

    @Override
    public void reviewSalaryStandard(ReviewSalaryStandardVo rssv) {
        // 1. 查询薪酬标准是否存在
        SalaryStandard dbSalaryStandard = this.lambdaQuery()
                .eq(SalaryStandard::getId, rssv.getId())
                .one();
        if (dbSalaryStandard == null) {
            throw new CustomException("薪酬标准不存在");
        }

        // 2. 设置薪酬项目对应的金额
        List<RegisterSalaryItemMoneyVo> moneyVoList = rssv.getRegisterSalaryItemMoneyVoList();
        for (RegisterSalaryItemMoneyVo vo : moneyVoList) {
            SalaryStandardItem salaryStandardItem = new SalaryStandardItem();
            salaryStandardItem.setSalaryStandardId(rssv.getId());
            salaryStandardItem.setSalaryItemId(vo.getId());
            salaryStandardItem.setMoney(vo.getMoney());

            // 条件修改
            LambdaQueryWrapper<SalaryStandardItem> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SalaryStandardItem::getSalaryStandardId, rssv.getId());
            queryWrapper.eq(SalaryStandardItem::getSalaryItemId, vo.getId());
            salaryStandardItemService.update(salaryStandardItem, queryWrapper);
        }

        // 3. 将薪酬项目的状态设置为'正常'
        dbSalaryStandard.setStatus("正常");
        dbSalaryStandard.setReviewId(StpUtil.getLoginIdAsInt());
        dbSalaryStandard.setReviewAt(Calendar.getInstance().getTime());
        dbSalaryStandard.setReviewComment(rssv.getComment());
        baseMapper.updateById(dbSalaryStandard);
    }

    public List<SalaryStandardListVo> getList(String typeStr) {
        // 1. 查询薪酬标准并封装成对应的vo
        List<SalaryStandardListVo> list = salaryStandardMapping.salaryStandardToSalaryStandardListVo(baseMapper.selectList(null));
        for (SalaryStandardListVo vo : list) {
            // 设置薪酬标准的项目集合
            vo.setSalaryItemMoneyVoList(baseMapper.getSalaryItemMoneyList(vo.getId()));
        }

        // 2. 根据状态过滤
        // 如果是查询全部，则直接返回
        if (typeStr.isBlank()) return list;

        // 如果不是指定的类型，就删除
        list.removeIf(s -> !typeStr.equals(s.getStatus()));

        return list;
    }

    public List<SalaryStandardListVo> conditionSearch(ConditionSearchVo conditionSearchVo) {
        // 1. 构建查询条件
        List<SalaryStandard> list = this.lambdaQuery()
                // ID模糊查询
                .like(conditionSearchVo.getId() != null,
                        SalaryStandard::getId,
                        conditionSearchVo.getId())
                // 登记时间范围
                .ge(conditionSearchVo.getStartTime() != null && !conditionSearchVo.getStartTime().isBlank(),
                        SalaryStandard::getCreatorAt,
                        conditionSearchVo.getStartTime())
                .le(conditionSearchVo.getEndTime() != null && !conditionSearchVo.getEndTime().isBlank(),
                        SalaryStandard::getCreatorAt,
                        conditionSearchVo.getEndTime())
                .list();

        // 2. 转换为VO对象
        List<SalaryStandardListVo> voList = salaryStandardMapping.salaryStandardToSalaryStandardListVo(list);

        // 3. 条件查询-关键字-名称
        if (!conditionSearchVo.getKeywords().isBlank()) {
            List<Integer> ids = employeeRecordService.queryIdByKeyWord(conditionSearchVo.getKeywords());
            voList.removeIf(vo ->
                    !(ids.contains(vo.getCreatorId())
                            || ids.contains(vo.getRegistrarId())
                            || ids.contains(vo.getReviewId())
                            || vo.getName().contains(conditionSearchVo.getKeywords())));
        }

        // 4. 设置每个薪酬标准的项目集合
        for (SalaryStandardListVo vo : voList) {
            vo.setSalaryItemMoneyVoList(baseMapper.getSalaryItemMoneyList(vo.getId()));
        }

        return voList;
    }

}




