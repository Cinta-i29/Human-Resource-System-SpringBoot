package com.gdou.mapper;

import com.gdou.pojo.entity.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zzhave
* @description 针对表【position(职位表)】的数据库操作Mapper
* @createDate 2024-11-25 23:23:53
* @Entity com.gdou.pojo.entity.Position
*/
@Mapper
public interface PositionMapper extends BaseMapper<Position> {

}




