package com.myli.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.PostSectionVo;
import com.myli.domain.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SectionDao extends BaseMapper<Section> {
    /**
     * 分页查询所有贴吧的帖子
     * @param page
     * @param sectionName
     * @return
     */
    List<PostSectionVo> selectAllVo(IPage<PostSectionVo> page, @Param("sectionName") String sectionName);
}
