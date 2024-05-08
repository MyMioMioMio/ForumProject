package com.myli.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.Posts;
import com.myli.domain.PostsUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 根据pid查询贴子内容
     * @param pid
     * @return
     */
    PostsUserVo selectByPidPostsUserVo(@Param("pid") Long pid);

    /**
     * 根据pid更新贴子点赞数
     * @param pid
     * @return
     */
    @Update("update posts set likes = likes + 1 where pid = #{pid}")
    Integer updateLikes(@Param("pid") Long pid);
}
