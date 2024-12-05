package com.gdou.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.mapper.BillMapper;
import com.gdou.mapper.SalaryStandardMapper;
import com.gdou.mapping.BillMapping;
import com.gdou.pojo.entity.Bill;
import com.gdou.pojo.vo.BillEmpListVo;
import com.gdou.pojo.vo.bill.*;
import com.gdou.pojo.vo.salary.SalaryItemMoneyVo;
import com.gdou.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * @author zzhave
 */
@Service
@RequiredArgsConstructor
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill>
        implements BillService {
    private final BillMapping billMapping;
    private final SalaryStandardMapper salaryStandardMapper;

    public List<BillEmpListVo> getEmpList(ConditionalSearchBillEmpVo csbev) {
        // 1. 使用SQL查询档案列表
        List<BillEmpListVo> list = baseMapper.getEmpList();
        // 2. 一级机构code条件为空，直接返回
        if (csbev.getFirstCode().isBlank()) {
            return list;
        }
        StringBuilder sb = new StringBuilder();
        // 3. 二级机构code条件为空，筛选掉一级机构code不符合的数据
        sb.append(String.format("%02d", Integer.parseInt(csbev.getFirstCode())));
        if (csbev.getSecondCode().isBlank()) {
            list.removeIf(billEmpListVo ->
                    !billEmpListVo.getRecordNumber().substring(4, 6).contentEquals(sb));
            return list;
        }
        // 4. 三级机构code条件为空，筛选掉一级机构code和二级机构code不符合的数据
        sb.append(String.format("%02d", Integer.parseInt(csbev.getSecondCode())));
        if (csbev.getThirdCode().isBlank()) {
            list.removeIf(billEmpListVo ->
                    !billEmpListVo.getRecordNumber().substring(4, 8).contentEquals(sb));
            return list;
        }
        // 5， 三级机构code条件不为空，筛选掉一级机构code和二级机构code和三级机构code不符合的数据
        sb.append(String.format("%02d", Integer.parseInt(csbev.getThirdCode())));
        list.removeIf(billEmpListVo ->
                !billEmpListVo.getRecordNumber().substring(4, 10).contentEquals(sb));
        return list;
    }

    public void registerBill(RegisterBillVo registerBillVo) {
        Bill bill = billMapping.registerBillVoToBill(registerBillVo);
        bill.setRegisterId(StpUtil.getLoginIdAsInt());
        bill.setRegisterTime(Calendar.getInstance().getTime());

        baseMapper.insert(bill);
    }

    public void reviewBill(ReviewBillVo reviewBillVo) {
        // 1. 根据账单ID查询旧数据
        Bill dbBill = baseMapper.selectById(reviewBillVo.getId());

        // 2. 设置数据
        dbBill.setAwardMoney(reviewBillVo.getAwardMoney());
        dbBill.setDeductionMoney(reviewBillVo.getDeductionMoney());
        dbBill.setReviewId(StpUtil.getLoginIdAsInt());
        dbBill.setReviewTime(Calendar.getInstance().getTime());
        dbBill.setReviewOpinion(reviewBillVo.getReviewOpinion());
        dbBill.setStatus("已发放");

        // 3. 更新数据
        baseMapper.updateById(dbBill);
    }

    public List<BillListVo> conditionalQueryBillList(ConditionalSearchBillVo conditionalSearchBillVo) {
        List<BillListVo> billListVos = baseMapper.conditionalQueryBillList(conditionalSearchBillVo);
        for (BillListVo billListVo : billListVos) {
            billListVo.setSalaryItemMoneyVoList(
                    salaryStandardMapper.getSalaryItemMoneyList(billListVo.getSalaryStandardId()));
            double SalaryStandardTotalMoney = 0;
            for (SalaryItemMoneyVo s : billListVo.getSalaryItemMoneyVoList()) {
                SalaryStandardTotalMoney += s.getIsDeduction() ? -s.getMoney() : s.getMoney();
            }
            billListVo.setSalaryStandardTotalMoney(SalaryStandardTotalMoney);
            billListVo.setBillTotalMoney(
                    SalaryStandardTotalMoney +
                            billListVo.getAwardMoney() - billListVo.getDeductionMoney());
        }
        return billListVos;
    }
}




