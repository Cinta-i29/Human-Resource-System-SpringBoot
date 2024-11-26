package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.pojo.entity.Position;
import com.gdou.service.PositionService;
import com.gdou.mapper.PositionMapper;
import org.springframework.stereotype.Service;

/**
* @author zzhave
* @description 针对表【position(职位表)】的数据库操作Service实现
* @createDate 2024-11-25 23:23:53
*/
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position>
    implements PositionService{

}




