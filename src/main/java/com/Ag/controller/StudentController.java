package com.Ag.controller;

import com.Ag.pojo.ApplicationForm;
import com.Ag.pojo.AssessmentApplicationVo;
import com.Ag.pojo.Result;
import com.Ag.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/stus")
    public Result  GetPersonalAllApply(HttpServletRequest request,@RequestParam(required = false) String category,@RequestParam(required = false)String  title){
        log.info("查询个人全部申报数据");
        String username = (String) request.getAttribute("username");
        List<AssessmentApplicationVo> personalproject =  studentService.GetPersonalApply(username,category,title);
        return Result.success(personalproject);
    }

    @DeleteMapping("/delete")
    public Result  DeleteStudent(HttpServletRequest request,@RequestParam(required = true) long id){
        log.info("删除申报id等于{}的申报",id);
        long Stu_id = (long) request.getAttribute("id");
        studentService.DeleteStu(Stu_id,id);
        return Result.success();
    }


    @PutMapping("/insert")
    public Result InsertStudent(HttpServletRequest request, @RequestBody(required = true)ApplicationForm applicationForm){
        log.info("增加申报");
        long Stu_id = (long) request.getAttribute("id");
        studentService.InsertApply(Stu_id,applicationForm);
        return Result.success();
    }

    @PutMapping("/update")
    public Result UpdateStudent(HttpServletRequest request, @RequestBody(required = true)ApplicationForm applicationForm){
        log.info("更新申报");
        studentService.UpdateApply(applicationForm);
        return Result.success();
    }



}
