package com.ivo.demo.quartz.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ivo.demo.common.JsonResult;
import com.ivo.demo.common.PageResult;
import com.ivo.demo.quartz.entity.JobEntity;
import com.ivo.demo.quartz.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangjian
 * @date 2018/8/30
 */
@Controller
@RequestMapping("/quartz/job")
public class JobController {

    private final static Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobService jobService;

    @GetMapping
    public String job() {
        return "quartz/job.html";
    }

    /**
     * 任务列表
     * @param jobEntity
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public PageResult<JobEntity> list(JobEntity jobEntity, Integer pageNo, Integer pageSize) {

        Page<JobEntity> page = jobService.listJob(jobEntity, pageNo, pageSize);

        return new PageResult<>(page.getTotal(), page.getRecords());
    }

    /**
     * 新增任务
     * @param jobEntity
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public JsonResult add(JobEntity jobEntity) {
        try {
            jobService.addJob(jobEntity);
            return JsonResult.ok("任务新增成功");
        } catch (Exception e) {
            return JsonResult.error().put("error", e.getMessage());
        }
    }

    /**
     * 触发任务
     * @param jobEntity
     * @return
     */
    @PostMapping("/trigger")
    @ResponseBody
    public JsonResult trigger(JobEntity jobEntity) {
        jobService.triggerJob(jobEntity);
        return JsonResult.ok("任务触发成功");
    }

    /**
     * 暂停任务
     * @param jobEntity
     * @return
     */
    @PostMapping("/pause")
    @ResponseBody
    public JsonResult pause(JobEntity jobEntity) {
        jobService.pauseJob(jobEntity);
        return JsonResult.ok("任务暂停成功");
    }

    /**
     * 恢复任务
     * @param jobEntity
     * @return
     */
    @PostMapping("/resume")
    @ResponseBody
    public JsonResult resume(JobEntity jobEntity) {
        jobService.resumeJob(jobEntity);
        return JsonResult.ok("任务恢复成功");
    }

    /**
     * 移除任务
     * @param jobEntity
     * @return
     */
    @PostMapping("/remove")
    @ResponseBody
    public JsonResult remove(JobEntity jobEntity) {
        jobService.removeJob(jobEntity);
        return JsonResult.ok("任务移除成功");
    }

    @GetMapping("/addView")
    public String add() {
        return "quartz/add.html";
    }
}
