package com.myli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.Reply;
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
}
