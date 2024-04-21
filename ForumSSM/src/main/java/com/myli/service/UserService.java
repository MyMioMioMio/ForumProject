package com.myli.service;

import com.myli.domain.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User selectByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Boolean selectByUsername(String username);

    /**
     * 新建用户
     * @param user
     * @return
     */
    public Integer insertUser(User user);
}
