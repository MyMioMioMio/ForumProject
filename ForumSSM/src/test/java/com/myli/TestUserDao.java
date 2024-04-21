package com.myli;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.myli.dao.UserDao;
import com.myli.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserDao {
    @Autowired
    private UserDao userDao;


    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("Testttt");
        user.setPassword("123456");
        int i = userDao.insert(user);
        System.out.println(user);
    }

    @Test
    public void testSelectUser() {
        User user = userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, "QAQaaaa").eq(User::getPassword, "123456"));
        System.out.println(user);
    }
}
