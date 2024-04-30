package com.myli;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myli.dao.ReplyLikesDao;
import com.myli.domain.ReplyLikes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestReplyLikesDao {
    @Autowired
    private ReplyLikesDao replyLikesDao;

    @Test
    public void testSelectCount() {
        LambdaQueryWrapper<ReplyLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ReplyLikes::getRid, 1L);
        Long l = replyLikesDao.selectCount(wrapper);
        System.out.println(l);
    }

    @Test
    public void testExist() {
        LambdaQueryWrapper<ReplyLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ReplyLikes::getRid, 5L);
        wrapper.eq(ReplyLikes::getUid, 200005L);
        boolean b = replyLikesDao.exists(wrapper);
        System.out.println(b);
    }
}
