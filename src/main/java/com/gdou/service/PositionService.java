package com.gdou.service;

import com.gdou.pojo.entity.Position;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.pojo.vo.position.AddPositionVo;
import com.gdou.pojo.vo.position.UpdatePositionVo;

/**
* @author zzhave
* @description 针对表【position(职位表)】的数据库操作Service
* @createDate 2024-11-25 23:23:53
*/
public interface PositionService extends IService<Position> {

    Position addPosition(AddPositionVo addPositionVo);

    void deletePosition(Long positionId);

    Position updatePosition(UpdatePositionVo updatePositionVo);
}
