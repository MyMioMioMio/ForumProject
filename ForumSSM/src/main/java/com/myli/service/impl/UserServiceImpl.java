package com.myli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myli.dao.UserDao;
import com.myli.domain.User;
import com.myli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User selectByUsernameAndPassword(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //判断条件
        queryWrapper.eq(User::getUsername, username).eq(User::getPassword, password);
        return userDao.selectOne(queryWrapper);
    }
}
