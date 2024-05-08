package com.myli.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myli.dao.PostsDao;
import com.myli.dao.PostsLikesDao;
import com.myli.domain.Posts;
import com.myli.domain.PostsLikes;
import com.myli.domain.PostsUserVo;
import com.myli.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService{
    @Autowired
    private PostsDao postsDao;

    @Autowired
    private PostsLikesDao postsLikesDao;

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

    @Override
    public Integer insertPostsLikes(PostsLikes postsLikes) {
        //更新post的likes
        postsDao.updateLikes(postsLikes.getPid());
        //新增贴子点赞
        return postsLikesDao.insert(postsLikes);
    }

    @Override
    public Boolean selectUserLikes(PostsLikes postsLikes) {
        LambdaQueryWrapper<PostsLikes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PostsLikes::getPid, postsLikes.getPid())
                .eq(PostsLikes::getUid, postsLikes.getUid());
        return postsLikesDao.exists(wrapper);
    }
}
