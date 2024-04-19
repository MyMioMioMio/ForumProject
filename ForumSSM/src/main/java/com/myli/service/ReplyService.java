package com.myli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.ReplyUserVo;

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
}
