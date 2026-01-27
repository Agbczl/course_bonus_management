package com.Ag.service.impl;

import com.Ag.mapper.ApplyMaterialMapper;
import com.Ag.mapper.StuMapper;
import com.Ag.pojo.ApplicationForm;
import com.Ag.pojo.AssessmentApplicationVo;
import com.Ag.pojo.User;
import com.Ag.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentServiceimpl implements StudentService {

    @Autowired
    private StuMapper stuMapper;

    @Autowired
    private ApplyMaterialMapper materialMapper;

    @Override
    public User login(User user) {
        return stuMapper.getByUsernameAndPassword(
                user.getUsername(),
                user.getPassword()
        );
    }

    @Override
    public List<AssessmentApplicationVo> GetPersonalApply(String username,String category,String title) {
        List<AssessmentApplicationVo> rawResults =  stuMapper.SelectPersonalApply(username, category, title);
        List<AssessmentApplicationVo> processedResults = new ArrayList<>();
        for (AssessmentApplicationVo vo : rawResults) {
            String imageListStr = vo.getImageListStr();
            List<String> imageList = new ArrayList<>();
            if (imageListStr != null && !imageListStr.isEmpty()) {
                try {
                    imageList = Arrays.stream(imageListStr.split(","))
                            .filter(s -> s != null && !s.trim().isEmpty())
                            .collect(Collectors.toList());
                } catch (Exception e) {
                    System.err.println("Error processing image list string: " + imageListStr + ", Error: " + e.getMessage());
                }
            }
            vo.setImageList(imageList);
            vo.setImageListStr(null);
        }
        return rawResults;
    }

    @Override
    public void DeleteStu(long username, List<Long> ids) {
        materialMapper.deleteByApplyId(ids);
        stuMapper.DeleteAssessmentApplication(username,ids);
    }

    @Override
    @Transactional
    public void InsertApply(long Stu_id, AssessmentApplicationVo assessmentApplicationVo) {
        stuMapper.InsertApplication(Stu_id, assessmentApplicationVo);
        Long applyId = assessmentApplicationVo.getId();
        if (assessmentApplicationVo.getImageList() != null && !assessmentApplicationVo.getImageList().isEmpty()) {
            materialMapper.insertBatch(applyId, assessmentApplicationVo.getImageList());
        }
    }
    @Override
    public void UpdateApply( AssessmentApplicationVo applicationForm) {
        stuMapper.UpdateApplication(applicationForm);
    }


    @Override
    public AssessmentApplicationVo GetById(Long id){
        AssessmentApplicationVo vo = stuMapper.selectid(id);
        List<String> images = materialMapper.selectByApplyId(id);
        vo.setImageList(images);
        return vo;
    }

    @Override
    public List<AssessmentApplicationVo> GetByCategory(String category) {
        return stuMapper.selectByCategory(category);
    }




}
