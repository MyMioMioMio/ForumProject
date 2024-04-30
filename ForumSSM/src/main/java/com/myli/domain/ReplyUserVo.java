package com.myli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;


@Data
public class ReplyUserVo{
    private Long rid;
    private Long uid;
    private Long pid;
    @TableField(value = "reply_description")
    private String replyDescription;
    @TableField(value = "to_rid")
    private Long toRid;
    @TableField(value = "reply_datetime")
    private Timestamp replyDateTime;
    private String username;
    //展示回复的回复的标志
    @TableField(exist = false)
    private Boolean toReplyVisible = false;
    private Long likes;
}
