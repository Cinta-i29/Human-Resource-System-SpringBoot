package com.gdou.mapper;

import com.gdou.pojo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zzhave
* @description 针对表【user(用户信息表)】的数据库操作Mapper
* @createDate 2024-11-25 23:24:05
* @Entity com.gdou.pojo.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




