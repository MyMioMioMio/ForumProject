package com.myli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("posts_likes")
@Data
public class PostsLikes {
    @TableId(type = IdType.AUTO)
    Long plid;
    Long uid;
    Long pid;
}
