package com.Ag.controller;

import com.Ag.pojo.JwtUtils;
import com.Ag.pojo.Result;
import com.Ag.pojo.User;
import com.Ag.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Component
public class logincontroller {

    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("用户登录：{}", user);

        User loginUser = studentService.login(user);

        if (loginUser != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", loginUser.getId());
            map.put("username", loginUser.getUsername());
            map.put("role", loginUser.getRole());
            String jwt = JwtUtils.generateJwt(map);
            return Result.success(jwt);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}
