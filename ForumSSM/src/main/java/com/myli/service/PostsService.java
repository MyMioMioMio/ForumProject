package com.myli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.Posts;
import com.myli.domain.PostsUserVo;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PostsService {

    /**
     * 根据吧sid查询帖子
     * @param current
     * @param pageSize
     * @param sid
     * @return
     */
    IPage<PostsUserVo> selectListVo(Integer current, Integer pageSize, Long sid);

    /**
     * 根据pid查询贴子
     * @param pid
     * @return
     */
    PostsUserVo selectByPidPostsUserVo(Long pid);

    /**
     * 新增帖子
     * @param posts
     * @return
     */
    Integer insertPost(Posts posts);
}
