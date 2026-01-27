package com.Ag.service.impl;

import com.Ag.mapper.StuMapper;
import com.Ag.pojo.ApplicationForm;
import com.Ag.pojo.AssessmentApplicationVo;
import com.Ag.service.TeacherApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherApplyServiceImpl implements TeacherApplyService {

    @Autowired
    private StuMapper applyMapper;

    @Override
    public List<AssessmentApplicationVo> listAll(String username, String status) {
        List<AssessmentApplicationVo> rawResults =  applyMapper.selectAll(username, status);
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
    public boolean updateApplicationStatus(Long applicationId, String newStatus){
        // 1. 可以先查询一下当前状态，做一些业务校验（例如，不能重复审核等）
        // ApplicationForm currentApp = applicationFormMapper.selectById(applicationId);
        // if (currentApp == null || !canReview(currentApp.getStatus())) { // 假设有个 canReview 规则方法
        //     return false;
        // }

        AssessmentApplicationVo updateForm = new AssessmentApplicationVo();
        updateForm.setId(applicationId);
        updateForm.setStatus(newStatus); // 设置新状态
        int rowsAffected = applyMapper.updateById(updateForm); // 或者使用 updateWrapper 指定条件

        // 3. 判断更新是否成功 (影响行数 > 0)
        return rowsAffected > 0;
    }
}
