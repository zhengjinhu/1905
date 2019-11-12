package com.jk.user.mapper;

import com.jk.user.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 17:00
 * @Version V1.0
 **/
public interface UserMapper {
    Integer queryAllCount();

    List queryUser(@Param("s") int startNum, @Param("r") int rows);

    void addUser(User user);

    void addUserRoles(List list);

    User queryUserByUsername(@Param("userName") String userName);
}
