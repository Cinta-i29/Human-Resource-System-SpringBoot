package com.gdou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.pojo.entity.Bill;
import com.gdou.pojo.vo.BillEmpListVo;
import com.gdou.pojo.vo.bill.ConditionalSearchBillEmpVo;

import java.util.List;

/**
* @author zzhave
*/
public interface BillService extends IService<Bill> {

    List<BillEmpListVo> getEmpList(ConditionalSearchBillEmpVo conditionalSearchBillEmpVo);
}
