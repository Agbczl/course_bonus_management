package com.Ag.controller;

import com.Ag.pojo.ApplicationForm;
import com.Ag.pojo.Result;
import com.Ag.service.TeacherApplyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
public class TeacherApplyController {

    @Autowired
    private TeacherApplyService teacherApplyService;

    @GetMapping("/list")
    public Result listAll(HttpServletRequest request,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String status
    ) {
        Long userid = (Long) request.getAttribute("id");
        return Result.success(
                teacherApplyService.listAll(userid,username, status)
        );
    }

    @PutMapping("/review")
    public Result review(@RequestBody ApplicationForm reviewData) {

        Long applicationId = reviewData.getId();
        String newStatus = reviewData.getStatus();
        String teacherComment = reviewData.getTeacherComment();

        if (applicationId == null) {
            return Result.error("申报ID不能为空");
        }

        if (!"已通过".equals(newStatus) && !"已驳回".equals(newStatus)) {
            return Result.error("无效的审核状态");
        }

        try {
            boolean success = teacherApplyService.updateApplicationStatusAndComment(
                    applicationId,
                    newStatus,
                    teacherComment
            );

            return success
                    ? Result.success("审核成功")
                    : Result.error("审核失败，数据不存在或已审核");

        } catch (Exception e) {
            log.error("审核异常", e);
            return Result.error("服务器内部错误");
        }
    }

}

