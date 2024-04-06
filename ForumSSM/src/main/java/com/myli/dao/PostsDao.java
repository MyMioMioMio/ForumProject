package com.myli.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myli.domain.Posts;
import com.myli.domain.PostsUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostsDao extends BaseMapper<Posts> {

    List<PostsUserVo> selectListVo();
}
