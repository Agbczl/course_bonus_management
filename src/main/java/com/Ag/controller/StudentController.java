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
@RestController()
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

    @DeleteMapping("/delete/{ids}")
    public Result  DeleteStudent(HttpServletRequest request,@PathVariable List<Long> ids){
        log.info("删除申报id等于{}的申报",ids);
        long Stu_id = (long) request.getAttribute("id");
        studentService.DeleteStu(Stu_id,ids);
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

    @GetMapping("/{id}")
    public Result  GetById(HttpServletRequest request,@PathVariable Long id){
        log.info("根据id查询申报");
        AssessmentApplicationVo assessmentApplicationVo = studentService.GetById(id);
        return Result.success(assessmentApplicationVo);
    }

    @GetMapping("/category")
    public Result  GetByCategory(@RequestParam(required = false) String category){
        log.info("老师端根据category条件查询");
        List<AssessmentApplicationVo> category_Apply = studentService.GetByCategory(category);
        return Result.success(category_Apply);

    }





}
