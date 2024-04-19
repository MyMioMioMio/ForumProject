package com.myli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.PostsUserVo;
import com.myli.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主要负责与贴子相关
 */
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostsService postsService;

    /**
     * 根据吧sid查询帖子
     *
     * @param sid
     * @return
     */
    @GetMapping("/{current}/{pageSize}/{sid}")
    public Result getPostsBySid(@PathVariable("current") Integer current,
                                @PathVariable("pageSize") Integer pageSize,
                                @PathVariable("sid") Long sid) {
        IPage<PostsUserVo> page = postsService.selectListVo(current, pageSize, sid);
        List<PostsUserVo> pageData = page.getRecords();
        Integer code = pageData != null ? Code.GET_SUCCESS : Code.GET_ERR;
        String msg = pageData != null ? "" : "加载失败，请重试！";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageData", page.getRecords());
        map.put("totalPage", page.getTotal());
        return new Result(code, map, msg);
    }


    /**
     * 保存所点击的帖子的pid
     * 跳转post页面
     * @param pid
     * @return
     */
    @GetMapping("/{pid}/{sid}")
    public Result toPost(@PathVariable("pid") Long pid, @PathVariable("sid") Long sid,HttpServletRequest request) {
        //保存pid到session域
        HttpSession session = request.getSession();
        session.setAttribute("pid", pid);
        session.setAttribute("sid", sid);
        //转到post页面
        return new Result(Code.SAVE_SUCCESS, "");
    }

    /**
     * 根据pid查询帖子
     * @param request
     * @return
     */
    @GetMapping
    public Result getPostByPid(HttpServletRequest request) {
        //从session域中获取pid
        HttpSession session = request.getSession();
        Long pid = (Long) session.getAttribute("pid");
        PostsUserVo postsUserVo = postsService.selectByPidPostsUserVo(pid);
        Integer code = postsUserVo != null ? Code.GET_SUCCESS : Code.GET_ERR;
        String msg = postsUserVo != null ? "" : "获取繁忙，请重试！";
        return new Result(code, postsUserVo, msg);
    }
}
