package com.Ag.controller;


import com.Ag.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile[] imageList) throws Exception {
        List<String> paths = new ArrayList<>();
        for (MultipartFile file : imageList) {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File("G:/apply_upload/" + filename);
            file.transferTo(dest);
            paths.add("/upload/" + filename);
        }
        return Result.success(paths);
    }


}
