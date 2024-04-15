package com.myli.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.Posts;
import com.myli.domain.PostsUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostsDao extends BaseMapper<Posts> {

    /**
     * 根据吧sid查询帖子
     * @param page
     * @param sid
     * @return
     */
    List<PostsUserVo> selectListVo(IPage<PostsUserVo> page, @Param("sid") Long sid);
}
