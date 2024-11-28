package com.gdou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.pojo.entity.EmployeeRecord;

import java.util.List;

/**
* @author howe
*/
public interface EmployeeRecordMapper extends BaseMapper<EmployeeRecord> {

    List<String> getMaxRecordNum(String formattedCode);

    EmployeeRecord selectByRecordNumber(String recordNumber);

    List<Integer> queryIdByKeyWord(String keyWord);
}




