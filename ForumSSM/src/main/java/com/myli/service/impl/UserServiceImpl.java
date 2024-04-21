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

    @Override
    public Boolean selectByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //判断条件
        queryWrapper.eq(User::getUsername, username);
        return userDao.selectCount(queryWrapper) > 0;
    }

    @Override
    public Integer insertUser(User user) {
        return userDao.insert(user);
    }
}
