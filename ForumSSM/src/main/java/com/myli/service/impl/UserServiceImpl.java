package com.myli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myli.controller.Code;
import com.myli.dao.UserDao;
import com.myli.domain.User;
import com.myli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //获取指定存储位置
    @Value("${FileRepository}")
    private String fileRepository;

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

    @Override
    public boolean upload(MultipartFile multipartFile, Long id, Integer pattern) {
        //原始父路径
        String parentPath = fileRepository + "/unknow";
        //文件类型
        String fileType = "unknowfile";
        //判断模式以生成父路径
        if (pattern.intValue() == Code.USER_AVATAR) {
            parentPath = fileRepository + "/user/" + id;
            fileType = "photo";
        }
        //获取原始文件名
        String originalFilename = multipartFile.getOriginalFilename();
        //获取文件后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //生成新的文件名
        String newName = id + "-" + fileType + suffix;
        File parentFile = new File(parentPath);
        //判断目录是否存在
        if (!parentFile.exists()) {
            //不存在，则创建多级目录
            parentFile.mkdirs();
        } else {
            //存在，则需删除原文件
            for (File file : Objects.requireNonNull(parentFile.listFiles())) {
                if (file.getName().contains(fileType)) {
                    file.delete();
                }
            }
        }
        File file = new File(parentFile, newName);
        //保存文件到本地
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            //保存不成功则返回false
            return false;
        }
        //保存成功
        return true;
    }

    @Override
    public ResponseEntity<Object> download(Long id, Integer pattern) {
        //原始父路径
        String parentPath = fileRepository + "/unknow";
        //文件类型
        String fileType = "unknowfile";
        //确认模式
        if (pattern.intValue() == Code.USER_AVATAR) {
            parentPath = fileRepository + "/" + "user" + "/" + id;
            fileType = "photo";
        }
        File parentFile = new File(parentPath);
        //判断目录是否存在
        if (!parentFile.exists()) {
            //不存在则结束
            return null;
        }
        //获取目标目录下的所有文件
        File[] files = parentFile.listFiles();
        //判断files是否为null
        if (files == null) {
            return null;
        }
        File targetFile = null;
        //遍历文件
        for (File file : files) {
            if (file.getName().contains(fileType)) {
                //获取目标文件
                targetFile = file;
            }
        }
        //判断目标文件是否存在
        if (targetFile == null) {
            return null;
        }
        //创建resource以用来创建之后的responseEntity对象
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
