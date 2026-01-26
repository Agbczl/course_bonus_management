package com.Ag.service;

import com.Ag.pojo.AssessmentApplicationVo;

import java.util.List;

public interface TeacherApplyService {
    List<AssessmentApplicationVo> listAll(String username, String status);

    boolean updateApplicationStatus(Long applicationId, String newStatus);
}

