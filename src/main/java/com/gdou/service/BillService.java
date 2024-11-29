package com.gdou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.pojo.entity.Bill;
import com.gdou.pojo.vo.BillEmpListVo;
import com.gdou.pojo.vo.bill.*;

import java.util.List;

/**
 * @author zzhave
 */
public interface BillService extends IService<Bill> {

    List<BillEmpListVo> getEmpList(ConditionalSearchBillEmpVo conditionalSearchBillEmpVo);

    void registerBill(RegisterBillVo registerBillVo);

    void reviewBill(ReviewBillVo reviewBillVo);

    List<BillListVo> conditionalQueryBillList(ConditionalSearchBillVo conditionalSearchBillVo);
}
