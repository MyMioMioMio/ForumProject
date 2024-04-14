package com.myli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.PostSectionVo;
import com.myli.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sections")
@CrossOrigin(origins = "*")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    /**
     * 获取吧+帖子
     * @param current
     * @param pageSize
     * @param sectionName
     * @return
     */
    @GetMapping("/{current}/{pageSize}/{sectionName}")
    public Result getSections(@PathVariable("current") Integer current,
                              @PathVariable("pageSize") Integer pageSize,
                              @PathVariable("sectionName") String sectionName
    ) {
        IPage<PostSectionVo> page = sectionService.selectAllVo(current, pageSize, sectionName);
        List<PostSectionVo> pageData = page.getRecords();
        Integer code = pageData != null ? Code.GET_SUCCESS : Code.GET_ERR;
        String msg = pageData != null ? "" : "加载失败，请重试！";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageData", page.getRecords());
        map.put("totalPage", page.getTotal());
        return new Result(code, map, msg);
    }


}
