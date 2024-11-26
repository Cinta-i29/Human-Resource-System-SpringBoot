package com.gdou.mapping;

import com.gdou.pojo.entity.User;
import com.gdou.pojo.vo.user.LoginUserVo;
import com.gdou.pojo.vo.user.UpdateUser;
import com.gdou.pojo.vo.user.UserLoginSuccessVo;
import org.mapstruct.Mapper;

/**
 * @author howe
 */
@Mapper(componentModel = "spring")
public interface UserMapping {
    User loginUserVoToUser(LoginUserVo loginUserVo);

    UserLoginSuccessVo userToUserLoginSuccessVo(User user);

    User updateUserToUser(UpdateUser updateUser);
}
