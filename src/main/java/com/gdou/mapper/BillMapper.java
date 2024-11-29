package com.gdou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.pojo.entity.Bill;
import com.gdou.pojo.vo.BillEmpListVo;
import com.gdou.pojo.vo.bill.BillListVo;
import com.gdou.pojo.vo.bill.ConditionalSearchBillVo;

import java.util.List;

/**
* @author howe
*/
public interface BillMapper extends BaseMapper<Bill> {

    List<BillEmpListVo> getEmpList();

    List<BillListVo> conditionalQueryBillList(ConditionalSearchBillVo conditionalSearchBillVo);
}




