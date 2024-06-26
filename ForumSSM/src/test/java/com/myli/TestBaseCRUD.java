package com.myli;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myli.dao.*;
import com.myli.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestBaseCRUD {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SectionDao sectionDao;
    @Autowired
    private ReplyDao replyDao;
    @Autowired
    private PostsDao postsDao;
    @Autowired
    private ReplyLikesDao replyLikesDao;

    @Test
    void testBaseUserDao() {
        List<User> users = userDao.selectList(null);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, "沦为阶下囚QAQ").eq(User::getPassword, "ljh3927322");
        User user = userDao.selectOne(queryWrapper);
        System.out.println(user);
        System.out.println("****************************************************************");
    }

    @Test
    void testBaseSectionDao() {
        List<Section> sections = sectionDao.selectList(null);
        System.out.println(sections);
        System.out.println("****************************************************************");
    }

    @Test
    void testBaseReplyDao() {
        List<Reply> replies = replyDao.selectList(null);
        System.out.println(replies);
        System.out.println("****************************************************************");
    }

    @Test
    void testBasePostsDao() {
        LambdaQueryWrapper<Posts> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.like(Posts::getPostsTitle, "aaa");
        List<Posts> posts = postsDao.selectList(queryWrapper);
        //List<PostsUserVo> postsUserVos = postsDao.selectListVo();
        System.out.println(posts);
        //System.out.println(postsUserVos);
        System.out.println("****************************************************************");
    }

    @Test
    void testBaseReplyLikesDao() {
        List<ReplyLikes> replyLikes = replyLikesDao.selectList(null);
        System.out.println(replyLikes);
    }
}
