package com.myli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.PostSectionVo;
import com.myli.domain.Section;
import com.myli.service.SectionService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主要负责与贴吧相关
 */
@RestController
@RequestMapping("/sections")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    /**
     * 处理index页面内容
     * 获取吧+帖子
     * @param current
     * @param pageSize
     * @return
     */
    @GetMapping("/{current}/{pageSize}")
    public Result getSections(@PathVariable("current") Integer current,
                              @PathVariable("pageSize") Integer pageSize) {
        IPage<PostSectionVo> page = sectionService.selectAllVo(current, pageSize);
        List<PostSectionVo> pageData = page.getRecords();
        Integer code = pageData != null ? Code.GET_SUCCESS : Code.GET_ERR;
        String msg = pageData != null ? "" : "加载失败，请重试！";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageData", page.getRecords());
        map.put("totalPage", page.getTotal());
        return new Result(code, map, msg);
    }

    /**
     * 保存所点击的贴吧sid
     * 跳转section页面
     * @param sid
     * @return
     */
    @GetMapping("/{sid}")
    public Result toSection(@PathVariable("sid") Long sid, HttpServletRequest request) {
        //保存sid到session域
        HttpSession session = request.getSession();
        session.setAttribute("sid", sid);
        //转到section页面
        return new Result(Code.SAVE_SUCCESS, "");
    }

    /**
     * 查询贴吧信息
     * @param request
     * @return
     */
    @GetMapping
    public Result getSection(HttpServletRequest request) {
        //根据之前的session域来查找sid
        HttpSession session = request.getSession();
        Long sid = (Long) session.getAttribute("sid");
        Section section = sectionService.selectById(sid);
        Integer code = section != null ? Code.GET_SUCCESS : Code.GET_ERR;
        String msg = section != null ? "" : "贴吧不存在！";
        return new Result(code, section, msg);
    }
}
