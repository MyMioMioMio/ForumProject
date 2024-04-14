package com.myli;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myli.dao.SectionDao;
import com.myli.domain.PostSectionVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestSectionDao {
    @Autowired
    private SectionDao sectionDao;

    @Test
    public void testSelectAllVo() {
        IPage<PostSectionVo> page = new Page<>(1, 2);
        LambdaQueryWrapper<PostSectionVo> query = new LambdaQueryWrapper<>();
//        query.like(PostSectionVo::getSectionName, "");
        List<PostSectionVo> postSectionVos = sectionDao.selectAllVo(page, "%%");
        System.out.println(postSectionVos);
        System.out.println("当前页码==>" + page.getCurrent());
        System.out.println("每页显示条数==>" + page.getSize());
        System.out.println("一共多少页==>" + page.getPages());
        System.out.println("一共多少条数据==>" + page.getTotal());
    }
}
