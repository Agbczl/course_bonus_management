package com.Ag.controller;

import com.Ag.pojo.ApplicationForm;
import com.Ag.pojo.AssessmentApplicationVo;
import com.Ag.pojo.Result;
import com.Ag.service.StudentService;
import com.Ag.service.TeacherApplyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects; // 用于 equals 比较

@Slf4j
@RestController
public class TeacherApplyController {

    @Autowired
    private TeacherApplyService teacherApplyService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public Result listAll(HttpServletRequest request,
                          @RequestParam(required = false) String username,
                          @RequestParam(required = false) String status
    ) {
        Long userid = (Long) request.getAttribute("id");
        return Result.success(
                teacherApplyService.listAll(userid, username, status)
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

        if (!Objects.equals("已通过", newStatus) && !Objects.equals("已驳回", newStatus)) {
            return Result.error("无效的审核状态");
        }

        try {
            // 3. 获取审核前的记录状态，用于判断是否需要重新计算
            AssessmentApplicationVo originalRecord = studentService.GetById(applicationId);
            if (originalRecord == null) {
                return Result.error("申报记录不存在");
            }
            String originalStatus = originalRecord.getStatus();
            Long studentId = originalRecord.getStudentId();

            // 执行审核操作
            boolean success = teacherApplyService.updateApplicationStatusAndComment(
                    applicationId,
                    newStatus,
                    teacherComment
            );

            if (!success) {
                return Result.error("审核失败，数据不存在或已审核");
            }

            // 4. 关键逻辑：如果状态发生了变化（特别是变为“已通过”），则重新计算该学生的模块成绩
            if (!Objects.equals(originalStatus, newStatus)) {
                log.info("申报 {} 状态从 {} 变更为 {}, 准备重新计算学生 {} 的模块成绩", applicationId, originalStatus, newStatus, studentId);
                // 调用 Service 方法来重新计算并同步
                teacherApplyService.recalculateAndSyncScoringModule(studentId);
                log.info("学生 {} 的模块成绩已重新计算并同步", studentId);
            } else {
                log.debug("申报 {} 状态未变，无需重新计算模块成绩", applicationId);
            }

            return Result.success("审核成功");

        } catch (Exception e) {
            log.error("审核异常", e);
            return Result.error("服务器内部错误");
        }
    }
}