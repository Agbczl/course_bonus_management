package com.Ag.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationForm {
    private Long id;
    private  String title;
    private  String category;
    private String status;
    private String description;
    private Long score;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}

//插入数据时使用
