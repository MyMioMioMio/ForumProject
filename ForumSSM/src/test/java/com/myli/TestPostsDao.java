package com.myli;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myli.dao.PostsDao;
import com.myli.domain.PostsUserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestPostsDao {
    @Autowired
    private PostsDao postsDao;

    @Test
    public void testSelectListVo() {
        IPage<PostsUserVo> page = new Page<>(1, 2);
        List<PostsUserVo> postsUserVos = postsDao.selectListVo(page, 200009L);
        System.out.println(postsUserVos);
        System.out.println("当前页码==>" + page.getCurrent());
        System.out.println("每页显示条数==>" + page.getSize());
        System.out.println("一共多少页==>" + page.getPages());
        System.out.println("一共多少条数据==>" + page.getTotal());
    }

    @Test
    public void testSelectByPid() {
        PostsUserVo postsUserVo = postsDao.selectByPidPostsUserVo(1012L);
        System.out.println(postsUserVo);
    }
}
