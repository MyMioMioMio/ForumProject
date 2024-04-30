package com.myli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.Reply;
import com.myli.domain.ReplyLikes;
import com.myli.domain.ReplyUserVo;
import org.springframework.transaction.annotation.Transactional;

//开启事务
@Transactional
public interface ReplyService {

    /**
     * 根据贴子id查询回复
     * @param pid
     * @return
     */
    IPage<ReplyUserVo> selectReplyUserVoByPid(Integer current, Integer pageSize, Long pid);

    /**
     * 根据回复rid和所在贴子的pid查询回复的回复
     * @param current
     * @param pageSize
     * @param pid
     * @param rid
     * @return
     */
    IPage<ReplyUserVo> selectToReplyByRidAndPid(Integer current, Integer pageSize, Long pid, Long rid);

    /**
     * 新增回复
     * @param reply
     * @return
     */
    Integer insertReply(Reply reply);

    /**
     * 查询回复点赞数
     * @param rid
     * @return
     */
    public Long selectReplyLikesCount(Long rid);

    /**
     * 新增回复点赞并保证一致性
     * @param replyLikes
     * @return
     */
    public Integer insertReplyLikes(ReplyLikes replyLikes);

    /**
     * 查询用户是否已经点过赞
     * @param replyLikes
     * @return
     */
    public Boolean selectUserLike(ReplyLikes replyLikes);
}
