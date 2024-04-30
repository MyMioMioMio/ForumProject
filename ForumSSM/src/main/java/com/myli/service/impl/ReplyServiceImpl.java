package com.myli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myli.dao.ReplyDao;
import com.myli.dao.ReplyLikesDao;
import com.myli.domain.Reply;
import com.myli.domain.ReplyLikes;
import com.myli.domain.ReplyUserVo;
import com.myli.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private ReplyLikesDao replyLikesDao;

    @Override
    public IPage<ReplyUserVo> selectReplyUserVoByPid(Integer current, Integer pageSize, Long pid) {
        IPage<ReplyUserVo> page = new Page<>(current, pageSize);
        List<ReplyUserVo> replyUserVos = replyDao.selectReplyByPid(page, pid);
        page.setRecords(replyUserVos);
        return page;
    }

    @Override
    public IPage<ReplyUserVo> selectToReplyByRidAndPid(Integer current, Integer pageSize, Long pid, Long rid) {
        IPage<ReplyUserVo> page = new Page<>(current, pageSize);
        List<ReplyUserVo> replyUserVos = replyDao.selectToReplyByRidAndPid(page, pid, rid);
        page.setRecords(replyUserVos);
        return page;
    }

    @Override
    public Integer insertReply(Reply reply) {
        return replyDao.insert(reply);
    }

    @Override
    public Long selectReplyLikesCount(Long rid) {
        LambdaQueryWrapper<ReplyLikes> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReplyLikes::getRid, rid);
        return replyLikesDao.selectCount(queryWrapper);
    }

    @Override
    public Integer insertReplyLikes(ReplyLikes replyLikes) {
        //更新点赞数
        replyDao.updateLikes(replyLikes.getRid());
        //记录点赞
        return replyLikesDao.insert(replyLikes);
    }

    @Override
    public Boolean selectUserLike(ReplyLikes replyLikes) {
        LambdaQueryWrapper<ReplyLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ReplyLikes::getRid, replyLikes.getRid())
                .eq(ReplyLikes::getUid, replyLikes.getUid());
        return replyLikesDao.exists(wrapper);
    }
}
