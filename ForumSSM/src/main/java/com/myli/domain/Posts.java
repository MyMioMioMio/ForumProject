package com.myli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Posts {
    @TableId(type = IdType.AUTO)
    private Long pid;
    private Long uid;
    @TableField(value = "posts_title")
    private String postsTitle;
    @TableField(value = "posts_description")
    private String postsDescription;
    private Long sid;
}
