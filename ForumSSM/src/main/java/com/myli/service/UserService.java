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

}
