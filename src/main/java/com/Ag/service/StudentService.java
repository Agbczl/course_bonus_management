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
    //根据id删除(包含批量删除)
    void DeleteStu(long username, List<Long> ids);
    //根据学生id对应插入
    void InsertApply(long Stu_id, AssessmentApplicationVo assessmentApplicationVo);
    //更新数据
    void UpdateApply(AssessmentApplicationVo applicationForm);
    //根据id查询数据
    AssessmentApplicationVo GetById(Long id);
    List<AssessmentApplicationVo> GetByCategory(String category);

}
