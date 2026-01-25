package com.Ag.controller;

import com.Ag.pojo.Result;
import com.Ag.service.TeacherApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherApplyController {

    @Autowired
    private TeacherApplyService teacherApplyService;

    @GetMapping("/list")
    public Result listAll(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String status
    ) {
        return Result.success(
                teacherApplyService.listAll(username, status)
        );
    }
}

