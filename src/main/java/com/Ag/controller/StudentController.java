package com.Ag.controller;

import com.Ag.pojo.AssessmentApplication;
import com.Ag.pojo.AssessmentApplicationVo;
import com.Ag.pojo.Result;
import com.Ag.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.List;

@Slf4j
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/stus")
    public Result  list(HttpServletRequest request){
        log.info("查询个人全部申报数据");
        String username = request.getHeader("username");
        List<AssessmentApplicationVo> personalproject =  studentService.list(username);
        return Result.success(personalproject);
    }
}
