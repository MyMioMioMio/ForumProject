package com.myli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myli.dao.SectionDao;
import com.myli.domain.PostSectionVo;
import com.myli.domain.Section;
import com.myli.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionDao sectionDao;
    @Override
    public IPage<PostSectionVo> selectAllVo(Integer current, Integer pageSize, String sectionName) {
        IPage<PostSectionVo> page = new Page<>(current, pageSize);
        //判断有无查询条件
        if (sectionName != null && !sectionName.isEmpty() && !sectionName.equals("nodata")) {
            sectionName = "%" + sectionName + "%";
        } else {
            sectionName = "%%";
        }
        List<PostSectionVo> postSectionVos = sectionDao.selectAllVo(page, sectionName);
        page.setRecords(postSectionVos);
        return page;
    }
}
