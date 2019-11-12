package com.jk.user.service;

import com.jk.user.model.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserService
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 17:00
 * @Version V1.0
 **/
public interface UserService {
    Map queryUser(int page, int rows);

    void addUser(User user);

    User queryUserByUsername(String userName);

}
