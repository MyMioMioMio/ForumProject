package com.myli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("reply_likes")
@Data
public class ReplyLikes {
    @TableId(type = IdType.AUTO)
    Long lid;
    Long uid;
    Long rid;
}
