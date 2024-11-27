package com.gdou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.exception.CustomException;
import com.gdou.mapper.PositionMapper;
import com.gdou.pojo.entity.EmployeeRecord;
import com.gdou.pojo.entity.Position;
import com.gdou.pojo.vo.position.AddPositionVo;
import com.gdou.pojo.vo.position.UpdatePositionVo;
import com.gdou.service.EmployeeRecordService;
import com.gdou.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author zzhave
 * @description 针对表【position(职位表)】的数据库操作Service实现
 * @createDate 2024-11-25 23:23:53
 */
@Service
@RequiredArgsConstructor
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position>
        implements PositionService {
    private final EmployeeRecordService employeeRecordService;

    public Position addPosition(AddPositionVo addPositionVo) {
        Position position = new Position();
        position.setName(addPositionVo.getName());
        LambdaQueryWrapper<Position> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Position::getName, addPositionVo.getName());
        Position one = this.getOne(wrapper);
        if (one == null) {
            this.save(position);
            return position;
        }
        throw new CustomException("职位名称已存在");
    }

    public void deletePosition(Long positionId) {
        // 1. 查询职位是否存在
        Position position = this.getById(positionId);
        if (position == null) {
            throw new CustomException("职位不存在");
        }

        // 2. 查询是否有人使用该职位
        employeeRecordService.lambdaQuery().eq(EmployeeRecord::getPositionId, positionId).oneOpt()
                .ifPresent(employeeRecord -> {
                    throw new CustomException("该职位已被员工使用,无法删除");
                });

        // 3. 删除职位
        this.removeById(positionId);
    }

    public Position updatePosition(UpdatePositionVo updatePositionVo) {
        // 1. 查询职位是否存在
        Position position = this.getById(updatePositionVo.getId());
        if (position == null) {
            throw new CustomException("职位不存在");
        }
        // 2. 查询名称是否重复
        LambdaQueryWrapper<Position> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Position::getName, updatePositionVo.getName());
        Position one = this.getOne(wrapper);
        if (one != null) {
            throw new CustomException("职位名称已存在");
        }
        // 3. 修改新名称
        position.setName(updatePositionVo.getName());
        baseMapper.updateById(position);
        return position;
    }
}




