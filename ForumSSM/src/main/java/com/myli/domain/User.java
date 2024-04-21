package com.myli.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long uid;
    private String username;
    @TableField(select = false)
    private String password;
    private String gender;
    @TableField(value = "user_signature")
    private String userSignature;
    @TableField(value = "user_photo")
    private String userPhoto;
    @TableField(value = "user_datetime")
    private Timestamp userDatetime;
    @TableField(exist = false)
    private Boolean remember;
}
