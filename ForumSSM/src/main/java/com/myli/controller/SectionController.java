package com.myli.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myli.domain.PostSectionVo;
import com.myli.domain.Section;
import com.myli.service.SectionService;
import com.myli.service.UserService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private UserService userService;

    /**
     * 处理index页面内容
     * 获取吧+帖子
     *
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
     *
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
     *
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

    /**
     * 分页查询所有贴吧
     * @param current
     * @param pageSize
     * @return
     */
    @GetMapping("/all/{current}/{pageSize}")
    public Result getAllSections(@PathVariable("current") Integer current,
                                 @PathVariable("pageSize") Integer pageSize) {
        IPage<Section> page = sectionService.selectAllSection(current, pageSize);
        List<Section> pageData = page.getRecords();
        Integer code = pageData != null ? Code.GET_SUCCESS : Code.GET_ERR;
        String msg = pageData != null ? "" : "加载失败，请重试！";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageData", pageData);
        map.put("totalPage", page.getTotal());
        return new Result(code, map, msg);
    }

    /**
     * 新添贴吧
     * @param section
     * @param request
     * @return
     */
    @PostMapping
    public Result AddSection(@RequestBody Section section, HttpServletRequest request) {
        //登录检测
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            //未登录不能新增贴子
            return new Result(Code.LOGIN_ERR, null, "请先登录！");
        }
        Integer i = sectionService.insertSection(section);
        Integer code = i != null && i > 0 ? Code.SAVE_SUCCESS : Code.SAVE_ERR;
        String msg = i != null && i > 0 ? "新建成功!" : "网络繁忙，请稍后试!";
        return new Result(code, null, msg);
    }

    /**
     * 更新贴吧信息
     * @param section
     * @param request
     * @return
     */
    @PutMapping
    public Result updateSection(@RequestBody Section section, HttpServletRequest request) {
        //登录检测
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            //未登录不能新增贴子
            return new Result(Code.LOGIN_ERR, null, "请先登录！");
        }
        Integer i = sectionService.updateSection(section);
        Integer code = i != null && i > 0 ? Code.UPDATE_SUCCESS : Code.UPDATE_ERR;
        String msg = i != null && i > 0 ? "更改成功!" : "网络繁忙，请稍后试!";
        return new Result(code, null, msg);
    }

    /**
     * 上传贴吧头像
     * @param multipartFile
     * @param sid
     * @param request
     * @return
     */
    @PostMapping("/uploadavatar")
    public Result uploadAvatar(@RequestParam("file") MultipartFile multipartFile,
                               @RequestParam("sid") Long sid,
                               HttpServletRequest request) {
        //登录身份验证
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            return new Result(Code.LOGIN_ERR, null, "请先登录!");
        }
        //保存到本地
        boolean flag = userService.upload(multipartFile, sid, Code.SECTION_AVATAR);
        Integer code = flag ? Code.SAVE_SUCCESS : Code.SAVE_ERR;
        String msg = flag ? "上传成功" : "网络繁忙，请稍后再试!";
        return new Result(code, null, msg);
    }

    /**
     * 下载贴吧头像
     * @param sid
     * @return
     */
    @GetMapping("/download/{sid}")
    public ResponseEntity<Object> downloadAvatar(@PathVariable("sid") Long sid) {
        //获得下载对象
        ResponseEntity<Object> download = userService.download(sid, Code.SECTION_AVATAR);
        return download;
    }
}
