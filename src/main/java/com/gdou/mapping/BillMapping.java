package com.gdou.mapping;

import com.gdou.pojo.entity.Bill;
import com.gdou.pojo.vo.bill.RegisterBillVo;
import org.mapstruct.Mapper;

/**
 * @author howe
 */
@Mapper(componentModel = "spring")
public interface BillMapping {
    Bill registerBillVoToBill(RegisterBillVo registerBillVo);
}
