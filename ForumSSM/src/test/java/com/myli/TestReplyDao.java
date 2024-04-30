package com.myli;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myli.dao.ReplyDao;
import com.myli.domain.ReplyUserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestReplyDao {
    @Autowired
    private ReplyDao replyDao;

    @Test
    public void testSelectReplyByPid() {
        IPage<ReplyUserVo> page = new Page<>(1, 2);
        List<ReplyUserVo> replyUserVos = replyDao.selectReplyByPid(page, 1001L);
        System.out.println(replyUserVos);
    }

    @Test
    public void testSelectToReplyByRidAndPid() {
        IPage<ReplyUserVo> page = new Page<>(1, 5);
        List<ReplyUserVo> replyUserVos = replyDao.selectToReplyByRidAndPid(page, 1005L, 1L);
        System.out.println(replyUserVos);
    }

    @Test
    public void testUpdateLikes() {
        replyDao.updateLikes(5L);
    }
}
