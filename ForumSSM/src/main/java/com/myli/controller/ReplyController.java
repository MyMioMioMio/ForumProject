package com.myli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.Reply;
import com.myli.domain.ReplyUserVo;
import com.myli.service.ReplyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/replys")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    /**
     * 根据帖子id查询回复
     * @param current
     * @param pageSize
     * @param pid
     * @return
     */
    @GetMapping("/{current}/{pageSize}/{pid}")
    Result getReplysByPid(@PathVariable("current") Integer current,
                          @PathVariable("pageSize") Integer pageSize,
                          @PathVariable("pid") Long pid) {
        IPage<ReplyUserVo> page = replyService.selectReplyUserVoByPid(current, pageSize, pid);
        List<ReplyUserVo> replys = page.getRecords();
        Integer code = replys != null ? Code.GET_SUCCESS : Code.GET_ERR;
        String msg = replys != null ? "" : "获取繁忙，请重试！";
        Map<String, Object> map = new HashMap<>();
        map.put("replysData", replys);
        map.put("totalPage", page.getTotal());
        return new Result(code, map, msg);
    }

    /**
     * 根据回复id查询回复
     * @param current
     * @param pageSize
     * @param pid
     * @param rid
     * @return
     */
    @GetMapping("/{current}/{pageSize}/{pid}/{rid}")
    Result getToReplysByPidAndRid(@PathVariable("current") Integer current,
                                  @PathVariable("pageSize") Integer pageSize,
                                  @PathVariable("pid") Long pid,
                                  @PathVariable("rid") Long rid) {
        IPage<ReplyUserVo> page = replyService.selectToReplyByRidAndPid(current, pageSize, pid, rid);
        List<ReplyUserVo> replys = page.getRecords();
        Integer code = replys != null ? Code.GET_SUCCESS : Code.GET_ERR;
        String msg = replys != null ? "" : "获取繁忙，请重试！";
        Map<String, Object> map = new HashMap<>();
        map.put("replysData", replys);
        map.put("totalPage", page.getTotal());
        return new Result(code, map, msg);
    }

    /**
     * 新增回复
     * @param reply
     * @param request
     * @return
     */
    @PostMapping
    Result addReply(@RequestBody Reply reply, HttpServletRequest request) {
        //登录检测
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            //未登录不能新增贴子
            return new Result(Code.LOGIN_ERR, null, "请先登录！");
        }
        Integer i = replyService.insertReply(reply);
        Integer code = i > 0 ? Code.SAVE_SUCCESS : Code.SAVE_ERR;
        String msg = i > 0 ? "回复成功!" : "网络繁忙，请稍后试!";
        return new Result(code, null, msg);
    }
}
