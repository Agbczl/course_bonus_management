package com.Ag.service;

import com.Ag.pojo.AssessmentApplicationVo;

import java.util.List;

public interface TeacherApplyService {
    List<AssessmentApplicationVo> listAll(Long teacherId,String username, String status);

    boolean updateApplicationStatusAndComment(Long applicationId, String newStatus,String teacherComment);
}

