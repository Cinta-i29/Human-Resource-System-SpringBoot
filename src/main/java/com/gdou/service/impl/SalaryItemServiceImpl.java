package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.pojo.entity.SalaryItem;
import com.gdou.service.SalaryItemService;
import com.gdou.mapper.SalaryItemMapper;
import org.springframework.stereotype.Service;

/**
* @author zzhave
* @description 针对表【salary_item(薪酬项目表)】的数据库操作Service实现
* @createDate 2024-11-25 23:23:57
*/
@Service
public class SalaryItemServiceImpl extends ServiceImpl<SalaryItemMapper, SalaryItem>
    implements SalaryItemService{

}




