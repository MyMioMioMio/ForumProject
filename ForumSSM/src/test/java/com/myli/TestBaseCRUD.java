package com.myli;

import com.myli.dao.PostsDao;
import com.myli.dao.ReplyDao;
import com.myli.dao.SectionDao;
import com.myli.dao.UserDao;
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

    @Test
    void testBaseUserDao() {
        List<User> users = userDao.selectList(null);
        System.out.println(users);
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
        List<Posts> posts = postsDao.selectList(null);
        List<PostsUserVo> postsUserVos = postsDao.selectListVo();
        System.out.println(posts);
        System.out.println(postsUserVos);
        System.out.println("****************************************************************");
    }
}
