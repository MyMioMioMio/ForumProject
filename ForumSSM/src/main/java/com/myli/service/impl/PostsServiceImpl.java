package com.myli.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myli.dao.PostsDao;
import com.myli.domain.Posts;
import com.myli.domain.PostsUserVo;
import com.myli.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService{
    @Autowired
    private PostsDao postsDao;

    @Override
    public IPage<PostsUserVo> selectListVo(Integer current, Integer pageSize, Long sid) {
        IPage<PostsUserVo> page = new Page<>(current, pageSize);
        List<PostsUserVo> postsUserVos = postsDao.selectListVo(page, sid);
        page.setRecords(postsUserVos);
        return page;
    }

    @Override
    public PostsUserVo selectByPidPostsUserVo(Long pid) {
        return postsDao.selectByPidPostsUserVo(pid);
    }

    @Override
    public Integer insertPost(Posts posts) {
        return postsDao.insert(posts);
    }
}
