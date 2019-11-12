package com.jk.user.service.impl;

import com.jk.user.mapper.UserMapper;
import com.jk.user.model.User;
import com.jk.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author zjh
 * @Date 2019/11/4 17:01
 * @Version V1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map queryUser(int page, int rows) {
        Integer allCount = userMapper.queryAllCount();
        //计算开始查询条数
        int startNum = (page - 1) * rows;
        List list = userMapper.queryUser(startNum, rows);
        Map m = new HashMap();
        m.put("rows", list);
        m.put("total", allCount);
        return m;
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);

        String[] split = user.getRoleIds().split(",");
        List list = new ArrayList();
        for (String ri : split) {
            User u = new User();
            u.setUserId(user.getUserId());
            u.setRoleId(Integer.parseInt(ri));
            list.add(u);
        }
        userMapper.addUserRoles(list);
    }

    @Override
    public User queryUserByUsername(String userName) {
        return userMapper.queryUserByUsername(userName);
    }


}
