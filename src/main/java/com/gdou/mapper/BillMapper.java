package com.gdou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.pojo.entity.Bill;
import com.gdou.pojo.vo.BillEmpListVo;

import java.util.List;

/**
* @author howe
*/
public interface BillMapper extends BaseMapper<Bill> {

    List<BillEmpListVo> getEmpList();
}




