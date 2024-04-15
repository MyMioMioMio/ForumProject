package com.myli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostsUserVo {
    private Long pid;
    private Long uid;
    private String username;
    @TableField(value = "user_photo")
    private String userPhoto;
    @TableField(value = "posts_title")
    private String postsTitle;
    @TableField(value = "posts_description")
    private String postsDescription;
    @TableField(value = "posts_dateTime")
    private Timestamp postsDateTime;
}
