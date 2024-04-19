package com.myli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.ReplyUserVo;
import com.myli.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
