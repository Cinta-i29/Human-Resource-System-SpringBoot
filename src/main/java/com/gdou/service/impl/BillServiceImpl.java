package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.mapper.BillMapper;
import com.gdou.pojo.entity.Bill;
import com.gdou.pojo.vo.BillEmpListVo;
import com.gdou.pojo.vo.bill.ConditionalSearchBillEmpVo;
import com.gdou.service.BillService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzhave
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill>
        implements BillService {

    public List<BillEmpListVo> getEmpList(ConditionalSearchBillEmpVo csbev) {
        // 1. 使用SQL查询档案列表
        List<BillEmpListVo> list = baseMapper.getEmpList();
        // 2. 一级机构code条件为空，直接返回
        if (csbev.getFirstCode().isBlank()) {
            return list;
        }
        // 3. 二级机构code条件为空，筛选掉一级机构code不符合的数据
        if (csbev.getSecondCode().isBlank()) {
            list.removeIf(billEmpListVo ->
                    !billEmpListVo.getRecordNumber().substring(4, 6).equals(csbev.getFirstCode()));
            return list;
        }
        // 4. 三级机构code条件为空，筛选掉一级机构code和二级机构code不符合的数据
        if (csbev.getThirdCode().isBlank()) {
            list.removeIf(billEmpListVo ->
                    !billEmpListVo.getRecordNumber().substring(4, 8).equals(csbev.getFirstCode() + csbev.getSecondCode()));
            return list;
        }
        // 5， 三级机构code条件不为空，筛选掉一级机构code和二级机构code和三级机构code不符合的数据
        list.removeIf(billEmpListVo ->
                !billEmpListVo.getRecordNumber().substring(4, 10).equals(csbev.getFirstCode() + csbev.getSecondCode() + csbev.getThirdCode()));
        return list;
    }
}




