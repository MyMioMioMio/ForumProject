package com.myli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.Posts;
import com.myli.domain.PostsUserVo;
import com.myli.service.PostsService;
import com.myli.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增帖子
     * @param posts
     * @return
     */
    @PostMapping
    public Result addPost(@RequestBody Posts posts, HttpServletRequest request) {
        //登录检测
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            //未登录不能新增贴子
            return new Result(Code.LOGIN_ERR, null, "请先登录！");
        }
        Integer i = postsService.insertPost(posts);
        //防止i为null
        if (i == null) {
            i = 0;
        }
        Integer code = i > 0 ? Code.SAVE_SUCCESS : Code.SAVE_ERR;
        String msg = i > 0 ? "发帖成功!" : "网络繁忙，请稍后试!";
        return new Result(code, null, msg);
    }
}
