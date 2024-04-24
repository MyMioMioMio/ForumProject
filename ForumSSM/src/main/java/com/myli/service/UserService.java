package com.myli.service;

import com.myli.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

//开启事务
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

    /**
     * 上传文件
     * @param multipartFile
     * @param id 用户id或贴吧id或文件id
     * @param pattern 模式码
     * @return
     */
    public boolean upload(MultipartFile multipartFile, Long id, Integer pattern);

    /**
     * 下载文件
     * @param id 用户id或贴吧id或文件id
     * @param pattern 模式码
     * @return
     */
    public ResponseEntity<Object> download(Long id, Integer pattern);
}
