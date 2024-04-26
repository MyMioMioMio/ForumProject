package com.myli.controller;

import com.myli.domain.User;
import com.myli.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @param request
     * @param response
     * @return
     */
    @PostMapping
    public Result login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        //查询user
        User selectUser = userService.selectByUsernameAndPassword(user.getUsername(), user.getPassword());
        Integer code;
        String msg = null;
        String data = null;
        if (selectUser != null) {
            //登录成功
            code = Code.LOGIN_SUCCESS;
            data = "/";
            msg = "登录成功!";
            //添加到服务器session
            request.getSession().setAttribute("user", selectUser);
            if (user.getRemember()) {
                //添加到cookie
                Cookie cookie1 = new Cookie("TieUsername", user.getUsername());
                Cookie cookie2 = new Cookie("TiePassword", user.getPassword());
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

    /**
     * 检查用户名
     * @param username
     * @return
     */
    @GetMapping("/{username}")
    public Result checkUsername(@PathVariable("username") String username) {
        return new Result(Code.GET_SUCCESS, userService.selectByUsername(username));
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result Register(@RequestBody User user) {
        Integer i = userService.insertUser(user);
        //防止Integer为null值
        if (i == null) {
            i = 0;
        }
        Integer code = i > 0 ? Code.REGISTER_SUCCESS : Code.REGISTER_ERR;
        String msg = i > 0 ? "注册成功!" : "注册失败!";
        return new Result(code, null, msg);
    }

    @GetMapping
    public Result getUserFromSession(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        Integer code = user != null ? Code.GET_SUCCESS : Code.GET_ERR;
        String msg = user != null ? "获取成功!" : "获取失败!";
        return new Result(code, user, msg);
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @GetMapping("/quitlogin")
    public Result quitLogin(HttpServletRequest request) {
        //清除session中的user对象
        request.getSession().removeAttribute("user");
        return new Result(Code.QUIT_SUCCESS, null, "退出登录成功!");
    }


    /**
     * 上传用户头像
     * @param multipartFile
     * @param uid
     * @return
     */
    @PostMapping("/uploadavatar")
    public Result uploadAvatar(@RequestParam("file") MultipartFile multipartFile,
                               @RequestParam("uid") Long uid,
                               HttpServletRequest request) {
        //登录身份验证
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            return new Result(Code.LOGIN_ERR, null, "请先登录!");
        }
        //保存文件到本地
        boolean flag = userService.upload(multipartFile, uid, Code.USER_AVATAR);
        Integer code = flag ? Code.SAVE_SUCCESS : Code.SAVE_ERR;
        String msg = flag ? "上传成功" : "网络繁忙，请稍后再试!";
        return new Result(code, null, msg);
    }

    /**
     * 下载用户头像
     * @param uid
     * @return
     */
    @GetMapping("/download/{uid}")
    public ResponseEntity<Object> downloadAvatar(@PathVariable("uid") Long uid) {
        //获得下载对象
        ResponseEntity<Object> download = userService.download(uid, Code.USER_AVATAR);
        return download;
    }
}
