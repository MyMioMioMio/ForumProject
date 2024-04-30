package com.myli.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostSectionVo {
    private Long pid;
    @TableField(value = "posts_title")
    private String postsTitle;
    @TableField(value = "posts_description")
    private String postsDescription;
    private Long sid;
    @TableField(value = "section_name")
    private String sectionName;
    @TableField(value = "section_photo")
    private String sectionPhoto;
    @TableField(value = "posts_dateTime")
    private Timestamp postsDateTime;
    private Long likes;
}
