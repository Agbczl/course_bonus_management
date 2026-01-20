package com.Ag.service.impl;

import com.Ag.mapper.StuMapper;
import com.Ag.pojo.AssessmentApplication;
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
    public List<AssessmentApplicationVo> list(String username) {
        return stuMapper.list(username);
    }

}
