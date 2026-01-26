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
    public Result listAll(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String status
    ) {
        return Result.success(
                teacherApplyService.listAll(username, status)
        );
    }

    @PutMapping("/review")
    public Result review(HttpServletRequest request, @RequestBody ApplicationForm reviewData){
        Long applicationId = reviewData.getId();
        String newStatus = reviewData.getStatus();
        try {
            // 验证状态值是否合法 (可选，但推荐)
            if (!"已通过".equals(newStatus) && !"已驳回".equals(newStatus)) {
                log.warn("无效的审核状态: {}", newStatus);
                return Result.error("无效的审核状态");
            }

            boolean success = teacherApplyService.updateApplicationStatus(applicationId, newStatus); // 或者 teacherService.reviewApplication(...)

            if (success) {
                log.info("审核操作成功，ID: {}, 新状态: {}", applicationId, newStatus);
                return Result.success("审核操作成功");
            } else {
                log.error("审核操作失败，可能因为ID不存在或状态更新不满足业务规则，ID: {}", applicationId);
                return Result.error("审核操作失败，数据可能已被修改或不存在");
            }

        } catch (Exception e) {
            log.error("审核过程中发生服务器异常", e);
            return Result.error("服务器内部错误");
        }
    }
    }

