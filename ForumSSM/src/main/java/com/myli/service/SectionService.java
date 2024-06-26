package com.myli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.PostSectionVo;
import com.myli.domain.Section;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//开启事务
@Transactional
public interface SectionService {
    /**
     * 分页查询所有贴吧的帖子
     * @param current 当前页
     * @param pageSize 页内条数
     * @return
     */
    IPage<PostSectionVo> selectAllVo(Integer current, Integer pageSize);


    /**
     * 根据sid查询贴吧
     * @param sid
     * @return
     */
    Section selectById(Long sid);

    /**
     * 分页查询所有贴吧
     * @param current
     * @param pageSize
     * @return
     */
    IPage<Section> selectAllSection(Integer current, Integer pageSize);

    /**
     * 新添贴吧
     * @param section
     * @return
     */
    Integer insertSection(Section section);

    /**
     * 更新贴吧
     * @param section
     * @return
     */
    Integer updateSection(Section section);
}
