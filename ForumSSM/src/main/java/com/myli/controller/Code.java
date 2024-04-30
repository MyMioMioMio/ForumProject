package com.myli.controller;

/**
 * 各种常量
 */
public class Code {
    /**
     * 结果状态码，告知前端，后端业务处理清空
     * 尾号为1，则为成功
     * 尾号为0，则为失败
     */
    public static Integer GET_SUCCESS = 20001;
    public static Integer SAVE_SUCCESS = 30001;
    public static Integer LOGIN_SUCCESS = 40001;
    public static Integer REGISTER_SUCCESS = 50001;
    public static Integer QUIT_SUCCESS = 60001;
    public static Integer UPDATE_SUCCESS = 70001;
    //点赞成功
    public static Integer LIKES_SUCCESS = 80001;

    public static Integer GET_ERR = 20000;
    public static Integer SAVE_ERR = 30000;
    public static Integer LOGIN_ERR = 40000;
    public static Integer REGISTER_ERR = 50000;
    public static Integer QUIT_ERR = 60000;
    public static Integer UPDATE_ERR = 70000;
    //点赞失败
    public static Integer LIKES_ERR = 80000;


    /**
     * 上传文件模式码
     */
    public static Integer USER_AVATAR = 90001;
    public static Integer SECTION_AVATAR = 100001;
}
