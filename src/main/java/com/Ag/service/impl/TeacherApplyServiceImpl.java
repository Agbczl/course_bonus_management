package com.Ag.service.impl;

import com.Ag.mapper.StuMapper;
import com.Ag.pojo.AssessmentApplicationVo;
import com.Ag.service.TeacherApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherApplyServiceImpl implements TeacherApplyService {

    @Autowired
    private StuMapper applyMapper;

    @Override
    public List<AssessmentApplicationVo> listAll(String username, String status) {
        return applyMapper.selectAll(username, status);
    }
}
