package com.Ag.service;

import com.Ag.pojo.ApplicationForm;
import com.Ag.pojo.AssessmentApplicationVo;
import com.Ag.pojo.User;

import java.util.List;

public interface StudentService {
    // 学生老师通用登录
    User login(User user);

    //根据学生id获取学生所有申报项目
    List<AssessmentApplicationVo> GetPersonalApply(String username,String category,String title);

    void DeleteStu(long username, long id);

    void InsertApply(long Stu_id, ApplicationForm assessmentApplicationVo);

    void UpdateApply(ApplicationForm applicationForm);
}
