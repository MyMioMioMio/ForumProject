package com.myli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("posts_likes")
public class PostsLikes {
    @TableId(type = IdType.AUTO)
    Long plid;
    Long uid;
    Long pid;
}
