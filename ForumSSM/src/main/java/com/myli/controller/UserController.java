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
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //获取指定存储位置
    @Value("${FileRepository}")
    private String fileRepository;
    
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
     * 上传demo
     * 之后要封装进service中的
     * @param multipartFile
     * @param uid
     * @return
     */
    @PostMapping("/upload")
    public Result upLoadDemo(@RequestParam("file") MultipartFile multipartFile, @RequestParam("uid") Long uid) {
        //获取原始文件名
        String originalFilename = multipartFile.getOriginalFilename();
        //获取文件后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //生成新的文件名
        String newName = uid + "-photo" + suffix;
        //文件父路径
        String parentPath = fileRepository + "/" + "user" + "/" + uid;
        File parentFile = new File(parentPath);
        //判断目录是否存在
        if (!parentFile.exists()) {
            //不存在，则创建多级目录
            parentFile.mkdirs();
        }
        File file = new File(parentFile, newName);
        //保存文件到本地
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(Code.SAVE_ERR, null, "网路错误，请重试!");
        }
        return new Result(Code.SAVE_SUCCESS, null, "保存成功!");
    }

    /**
     * 下载demo
     * @param uid
     */
    @GetMapping("/download/{uid}")
    public ResponseEntity<Object> downloadDemo(@PathVariable("uid") Long uid) {
        //文件父路径
        String parentPath = fileRepository + "/" + "user" + "/" + uid;
        File parentFile = new File(parentPath);
        //判断目录是否存在
        if (!parentFile.exists()) {
            //不存在则结束
            return null;
        }
        //获取目标目录下的所有文件
        File[] files = parentFile.listFiles();
        File targetFile = null;
        //遍历文件
        for (File file : files) {
            if (file.getName().contains("photo")) {
                //获取目标文件
                targetFile = file;
            }
        }
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(targetFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment;filename=\"%s", targetFile.getName()));
        headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        ResponseEntity<Object> responseEntity = ResponseEntity.ok()
                .headers(headers)
                .contentLength(targetFile.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
        return responseEntity;
    }
}
