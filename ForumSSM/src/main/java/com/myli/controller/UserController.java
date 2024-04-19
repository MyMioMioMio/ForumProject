package com.myli.controller;

import com.myli.domain.User;
import com.myli.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public Result login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        //查询user
        User selectUser = userService.selectByUsernameAndPassword(user.getUsername(), user.getPassword());
        Integer code;
        String msg = null;
        String data = null;
        if (selectUser != null) {
            //登录成功
            code = Code.LOGIN_SUCESS;
            data = "/";
            msg = "登录成功!";
            //添加到服务器session
            request.getSession().setAttribute("user", selectUser);
            if (user.getRemember()) {
                //添加到cookie
                Cookie cookie1 = new Cookie("TieUsername", selectUser.getUsername());
                Cookie cookie2 = new Cookie("TiePassword", selectUser.getPassword());
                //设置cookie时间
                cookie1.setMaxAge(5 * 24 * 60 * 60);
                cookie2.setMaxAge(5 * 24 * 60 * 60);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
        } else {
            //登录失败
            code = Code.LOGIN_ERR;
            msg = "登录失败,用户名或密码错误!";
        }
        return new Result(code, data, msg);
    }
}
