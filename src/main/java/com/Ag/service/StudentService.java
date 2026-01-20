package com.Ag.service;

import com.Ag.pojo.AssessmentApplication;
import com.Ag.pojo.AssessmentApplicationVo;
import com.Ag.pojo.User;

import java.util.List;

public interface StudentService {
    // 学生老师通用登录
    User login(User user);

    //根据学生id获取学生所有申报项目
    List<AssessmentApplicationVo> list(String username);
}
