package com.myli.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.Reply;
import com.myli.domain.ReplyUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyDao extends BaseMapper<Reply> {
    /**
     * 根据帖子id查询回复
     * @param page
     * @param pid
     * @return
     */
    @Select("select reply.*, username, user_photo from reply, user where reply.pid=#{pid} and reply.uid = user.uid and to_rid = 0 order by reply_datetime desc")
    List<ReplyUserVo> selectReplyByPid(IPage<ReplyUserVo> page, @Param("pid") Long pid);

    /**
     * 根据回复rid和所在贴子的pid查询回复的回复
     * @param page
     * @param pid
     * @param rid
     * @return
     */
    @Select("select reply.*, username, user_photo from reply, user where reply.pid=#{pid} and reply.uid = user.uid and to_rid = #{rid} order by reply_datetime desc")
    List<ReplyUserVo> selectToReplyByRidAndPid(IPage<ReplyUserVo> page, @Param("pid") Long pid, @Param("rid") Long rid);
}
