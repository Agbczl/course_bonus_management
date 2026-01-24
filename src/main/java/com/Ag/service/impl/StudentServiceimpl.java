package com.Ag.service.impl;

import com.Ag.mapper.StuMapper;
import com.Ag.pojo.ApplicationForm;
import com.Ag.pojo.AssessmentApplicationVo;
import com.Ag.pojo.User;
import com.Ag.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceimpl implements StudentService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public User login(User user) {
        return stuMapper.getByUsernameAndPassword(
                user.getUsername(),
                user.getPassword()
        );
    }

    @Override
    public List<AssessmentApplicationVo> GetPersonalApply(String username,String category,String title) {
        return stuMapper.SelectPersonalApply(username, category, title);
    }

    @Override
    public void DeleteStu(long username, long id) {
        stuMapper.DeleteAssessmentApplication(username,id);
    }


    @Override
    public void InsertApply(long Stu_id, ApplicationForm assessmentApplicationVo){
        stuMapper.InsertApplication(Stu_id,assessmentApplicationVo);
    }

    @Override
    public void UpdateApply( ApplicationForm applicationForm) {
        stuMapper.UpdateApplication(applicationForm);
    }

}
